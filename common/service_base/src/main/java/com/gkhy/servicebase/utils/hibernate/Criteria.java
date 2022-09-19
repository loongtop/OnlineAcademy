package com.gkhy.servicebase.utils.hibernate;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import java.io.Serializable;
import javax.persistence.criteria.*;

import com.gkhy.servicebase.utils.hibernate.criterion.impl.CriterionComplexImpl;
import com.gkhy.servicebase.utils.hibernate.criterion.ICriterion;
import com.gkhy.servicebase.utils.hibernate.criterion.impl.SimpleCriterionImpl;

/**
 * @Name: Criteria
 * @Description:
 * Example:
 *
 * public List<User> findByNameAndGender(String name,String gender){
 * Criteria<User> criteria = new Criteria<User>()
 *              .eq(StringUtil.isNotBlank(a),User::getName, name)
 *              .eq(StringUtil.isNotBlank(b),User::getGender, gender)
 *              .orderByDesc(User::getId);
 *this.repository.findAll(criteria);
 * }
 *
 * This tool is not suitable for object construction and multi-level association construction.
 * Parameter attribute check and parameter existence check have not been added for the time being.
 * Please use this tool to check whether the parameter type is consistent with the target attribute.
 *
 * Error: this.eq(Order::getMember,new Member()) It is not recommended to use the object as a query in this way, it will bring extra burden to the query, it is recommended to use this.eq("member.id",id)
 * Error: this.eq(Order::getMember::getId,1) The correct error should be this.eq("member.id",1), currently this query supports multi-level association this.eq("member.xx .xx.id",1)
 * @Author: leo
 * @Created: 2022-06-18
 * @Updated: 2022-06-18
 * @Version: 1.0
 **/

@Component
public class Criteria<T> implements Specification<T>, Serializable {

    private static final long serialVersionUID = -5054608522623557251L;

    // list of conditional expressions
    private final List<ICriterion> criterions = new ArrayList<>();
    // Sort expression list
    private final List<ICriterion> orders = new ArrayList<>();

    /**
     * functional interface
     *
     * @Param : <T>
     * @Param : <R>
     */
    @FunctionalInterface
    public interface SFunction<T, R> extends Function<T, R>, Serializable {
    }

    /**
     * Get the lambda expression field name
     *
     * @return the field name of the generic object
     * @Param : fn lambda expression
     * @Param : <T> generic
     */
    public static <T> String getFieldName(SFunction<T, ?> fn) {
        SerializedLambda serializedLambda = getSerializedLambda(fn);
        // Extract method, field, class, etc. from lambda information
        String fieldName = serializedLambda.getImplMethodName().substring("get".length());
        fieldName = fieldName.replaceFirst(fieldName.charAt(0) + "", (fieldName.charAt(0) + "").toLowerCase());
        return fieldName.replaceAll("[A-Z]", "$0");
    }

    /**
     * Check if the field exists in a class
     *
     * @Param : class c
     * @Param : fieldName field
     * whether @return exists
     */
    public static Boolean checkField(Class<?> c, String fieldName) {
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            if (fieldName.equals(f.getName())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Get the SerializedLambda object of the lambda expression
     *
     * @return SerializedLambda serialized lambda object
     * @Param : fn lambda expression
     * @Param : <T> generic
     */
    private static <T> SerializedLambda getSerializedLambda(SFunction<T, ?> fn) {
        //Take the serialization method out of the function
        Method writeReplaceMethod;
        try {
            writeReplaceMethod = fn.getClass().getDeclaredMethod("writeReplace");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        // Get serialized lambda information from serialize method
        writeReplaceMethod.setAccessible(true);
        try {
            return (SerializedLambda) writeReplaceMethod.invoke(fn);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Condition parameter converter, implements dynamic query method
     *
     * @return final query condition object, similar to hql statement
     * @Param : root The root object of the query, used to identify the entity class of the query
     * @Param : query top-level query object for custom query
     * @Param : builder query condition
     * @description Rewrites the dynamic parameter splicing method of Specification, converts all the collected condition functions in the criterionList into jpa Predicate objects, and uses the builder to assemble all parameter objects
     */
    @Override
    public Predicate toPredicate(Root<T> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        if (!criterions.isEmpty()) {
            // combine all conditions with and
            Predicate and = builder.and(criterions.stream()
                    .map(criterion -> criterion.toPredicate(root, query, builder))
                    .toArray(Predicate[]::new));
            query.where(and);
            List<Order> orders = this.orders.stream()
                    .map(order -> order.toOrder(root, query, builder))
                    .collect(Collectors.toList());

            query.orderBy(orders);
            return query.getRestriction();
        }
        return builder.conjunction();
    }

    /**
     * Add a multi-condition query
     * example
     * hql += and (xx=1 and xx like '%2%')
     * this.eq(new Criteria<xx>().eq(xx,1).like(xx,2));
     *
     * @return body
     * @Param : criteria criteria
     */
    public Criteria<T> add(Criteria<T> criteria) {
        List<ICriterion> criterionList = criteria.criterions;
        if (CollectionUtils.isEmpty(criterionList)) {
            return this;
        }
        this.criterions.add(new CriterionComplexImpl(criterionList, ICriterion.Operator.AND));
        return this;
    }


    /**
     * Add a multi-condition query
     *
     * @return body
     * @Param : whether to add the condition
     * @Param : criteria criteria
     */
    public Criteria<T> and(Boolean condition, Criteria<T> criteria) {
        if (condition) {
            return this.add(criteria);
        }
        return this;
    }

    /**
     * Add a multi-condition query
     *
     * @return body
     * @Param : whether to add the condition
     * @Param : criteria criteria
     */
    public Criteria<T> add(Boolean condition, Criteria<T> criteria) {
        if (condition) {
            return this.add(criteria);
        }
        return this;
    }

    /**
     * Add a multi-condition query
     * example
     * hql += and (xx=1 or xx=2)
     * this.eq(new Criteria<xx>().eq(xx,1).eq(xx,2));
     *
     * @return body
     * @Param : criteria criteria
     */
    public Criteria<T> or(Criteria<T> criteria) {
        List<ICriterion> criterionList = criteria.criterions;
        if (CollectionUtils.isEmpty(criterionList)) {
            return this;
        }
        this.criterions.add(new CriterionComplexImpl(criterionList, ICriterion.Operator.OR));
        return this;
    }

    /**
     * Add a multi-condition query
     *
     * @return body
     * @Param : whether to add the condition
     * @Param : criteria criteria
     */
    public Criteria<T> or(Boolean condition, Criteria<T> criteria) {
        if (condition) {
            return this.or(criteria);
        }
        return this;
    }

    /**
     * is equal to
     * example:
     * hql += "and id = 1"
     * this.eq(xx::getId,1);
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> eq(SFunction<T, ?> field, Object value) {
        criterions.add(new SimpleCriterionImpl(getFieldName(field), value, ICriterion.Operator.EQ));
        return this;
    }

    /**
     * is equal to
     * example:
     * hql += "and id = 1"
     * this.eq("id",1);
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> eq(String field, Object value) {
        criterions.add(new SimpleCriterionImpl(field, value, ICriterion.Operator.EQ));
        return this;
    }

    /**
     * If the condition is judged to pass, then add the method of eq
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> eq(boolean condition, SFunction<T, ?> field, Object value) {
        if (condition) {
            this.eq(field, value);
        }
        return this;
    }

    /**
     * If the condition is judged to pass, then add the method of eq
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> eq(boolean condition, String field, Object value) {
        if (condition) {
            this.eq(field, value);
        }
        return this;
    }

    /**
     * like
     * example:
     * hql += "and id like '%1%'"
     * this.like(xx::getId,1);
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> like(SFunction<T, ?> field, Object value) {
        criterions.add(new SimpleCriterionImpl(getFieldName(field), value, ICriterion.Operator.LIKE));
        return this;
    }

    /**
     * like
     * example:
     * hql += "and id like '%1%'"
     * this.like("id",1);
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> like(String field, Object value) {
        criterions.add(new SimpleCriterionImpl(field, value, ICriterion.Operator.LIKE));
        return this;
    }

    /**
     * left like
     * example:
     * hql += "and id like '%1'"
     * this.like("id",1);
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> leftLike(SFunction<T, ?> field, Object value) {
        criterions.add(new SimpleCriterionImpl(getFieldName(field), value, ICriterion.Operator.LEFT_LIKE));
        return this;
    }

    /**
     * right like
     * example:
     * hql += "and id like '1%'"
     * this.like("id",1);
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> rightLike(SFunction<T, ?> field, Object value) {
        criterions.add(new SimpleCriterionImpl(getFieldName(field), value, ICriterion.Operator.RIGHT_LIKE));
        return this;
    }

    /**
     * If the condition is judged to pass, then add the like method
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> like(boolean condition, SFunction<T, ?> field, Object value) {
        if (condition) {
            this.like(field, value);
        }
        return this;
    }

    /**
     * If the condition is judged to pass, then add the like method
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> like(boolean condition, String field, Object value) {
        if (condition) {
            this.like(field, value);
        }
        return this;
    }

    /**
     * is equal to
     * example:
     * hql += "and id <> 1"
     * this.ne(xx::getId,1);
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> ne(SFunction<T, ?> field, Object value) {
        criterions.add(new SimpleCriterionImpl(getFieldName(field), value, ICriterion.Operator.NE));
        return this;
    }

    /**
     * If the condition is judged to pass, then add the method of ne
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> ne(boolean condition, SFunction<T, ?> field, Object value) {
        if (condition) {
            this.ne(field, value);
        }
        return this;
    }

    /**
     * in query
     * example:
     * hql writing hql += "and id in ?"
     * Constructor writing this.in(xx::getId,Arrays.asList(1,2,3));
     *
     * @Param : field entity class field
     * @Param : coll array object list, set
     */
    public Criteria<T> in(SFunction<T, ?> field, Collection<?> coll) {
        criterions.add(new SimpleCriterionImpl(getFieldName(field), coll, ICriterion.Operator.IN));
        return this;
    }

    /**
     * If the condition is judged to pass, then the method of adding in
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> in(boolean condition, SFunction<T, ?> field, Collection<?> value) {
        if (condition) {
            this.in(field, value);
        }
        return this;
    }

    /**
     * in query
     * example:
     * hql writing hql += "and id in ?"
     * Constructor writing this.in("id",Arrays.asList(1,2,3));
     *
     * @Param : field entity class field
     * @Param : coll array object list, set
     */
    public Criteria<T> in(String field, Collection<?> coll) {
        criterions.add(new SimpleCriterionImpl(field, coll, ICriterion.Operator.IN));
        return this;
    }

    /**
     * If the condition is judged to pass, then the method of adding in
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> in(boolean condition, String field, Collection<?> value) {
        if (condition) {
            this.in(field, value);
        }
        return this;
    }

    /**
     * not In query
     * example:
     * hql writing hql += "and id not in (1,2,3)"
     * Constructor writing this.notIn(xx::getId,Arrays.asList(1,2,3));
     *
     * @Param : field entity class field
     * @Param : coll array object list, set
     */
    public Criteria<T> notIn(SFunction<T, ?> field, Collection<?> coll) {
        criterions.add(new SimpleCriterionImpl(getFieldName(field), coll, ICriterion.Operator.NOTIN));
        return this;
    }

    /**
     * If the condition is judged to pass, then add the notIn method
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> notIn(boolean condition, SFunction<T, ?> field, Collection<?> value) {
        if (condition) {
            this.notIn(field, value);
        }
        return this;
    }

    /**
     * in query
     * example:
     * hql writing hql += "and id > 1"
     * Constructor writing this.gt(xx::getId,1);
     *
     * @Param : field entity class field
     * @Param : value object
     */
    public Criteria<T> gt(SFunction<T, ?> field, Object value) {
        criterions.add(new SimpleCriterionImpl(getFieldName(field), value, ICriterion.Operator.GT));
        return this;
    }

    /**
     * If the condition is judged to pass, then the method of adding gt
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> gt(boolean condition, SFunction<T, ?> field, Object value) {
        if (condition) {
            this.gt(field, value);
        }
        return this;
    }

    /**
     * in query
     * example:
     * hql writing hql += "and id >= 1"
     * Constructor writing this.gte(xx::getId,1);
     *
     * @Param : field entity class field
     * @Param : value object
     */
    public Criteria<T> gte(SFunction<T, ?> field, Object value) {
        criterions.add(new SimpleCriterionImpl(getFieldName(field), value, ICriterion.Operator.GTE));
        return this;
    }

    /**
     * If the condition is judged to pass, then add the method of gte
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> gte(boolean condition, SFunction<T, ?> field, Object value) {
        if (condition) {
            this.gte(field, value);
        }
        return this;
    }

    /**
     * in query
     * example:
     * hql writing hql += "and id < 1"
     * Constructor writing this.lt(xx::getId,1);
     *
     * @Param : field entity class field
     * @Param : value object
     */
    public Criteria<T> lt(SFunction<T, ?> field, Object value) {
        criterions.add(new SimpleCriterionImpl(getFieldName(field), value, ICriterion.Operator.LT));
        return this;
    }

    /**
     * If the condition is judged to pass, then the method of adding lt
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> lt(boolean condition, SFunction<T, ?> field, Object value) {
        if (condition) {
            this.lt(field, value);
        }
        return this;
    }

    /**
     * in query
     * example:
     * hql writing hql += "and id <= 1"
     * Constructor writing this.lte(xx::getId,1);
     *
     * @Param : field entity class field
     * @Param : value object
     */
    public Criteria<T> lte(SFunction<T, ?> field, Object value) {
        criterions.add(new SimpleCriterionImpl(getFieldName(field), value, ICriterion.Operator.LTE));
        return this;
    }

    /**
     * If the condition is judged to pass, then add the lte method
     *
     * @Param : field entity class field
     * @Param : value
     */
    public Criteria<T> lte(boolean condition, SFunction<T, ?> field, Object value) {
        if (condition) {
            this.lte(field, value);
        }
        return this;
    }

    /**
     * isNull query
     * example:
     * hql writing hql += "and id is null "
     * Constructor writing this.isNull(xx::getId);
     *
     * @Param : field entity class field
     */
    public Criteria<T> isNull(SFunction<T, ?> field) {
        return this.isNull(getFieldName(field));
    }


    /**
     * isNull query
     * example:
     * hql writing hql += "and id is not null "
     * Constructor writing this.isNotNull("id");
     *
     * @Param : field entity class field
     */
    public Criteria<T> isNull(String field) {
        criterions.add(new SimpleCriterionImpl(field, null, ICriterion.Operator.ISNULL));
        return this;
    }

    /**
     * isNotNull query
     * example:
     * hql writing hql += "and id is not null "
     * Constructor writing this.isNotNull(xx::getId);
     *
     * @Param : field entity class field
     */
    public Criteria<T> isNotNull(SFunction<T, ?> field) {
        return this.isNotNull(getFieldName(field));
    }


    /**
     * isNotNull query
     * example:
     * hql writing hql += "and id is not null "
     * Constructor writing this.isNotNull("id");
     *
     * @Param : field entity class field
     */
    public Criteria<T> isNotNull(String field) {
        criterions.add(new SimpleCriterionImpl(field, null, ICriterion.Operator.IS_NOTNULL));
        return this;
    }

    /**
     * isEmpty query
     * example:
     * hql writing hql += "and id = '' "
     * Constructor writing this.isEmpty(xx::getId);
     *
     * @Param : field entity class field
     */
    public Criteria<T> isEmpty(SFunction<T, ?> field) {
        return this.isEmpty(getFieldName(field));
    }

    /**
     * isEmpty query
     * example:
     * hql writing hql += "and id = '' "
     * Constructor writing this.isEmpty("id");
     *
     * @Param : field entity class field
     */
    public Criteria<T> isEmpty(String field) {
        criterions.add(new SimpleCriterionImpl(field, null, ICriterion.Operator.IS_EMPTY));
        return this;
    }

    /**
     * isEmptyOrNull query
     * example:
     * hql writing hql += "and (id = '' or id is null)"
     * Constructor writing this.isEmptyOrNull("id");
     *
     * @Param : field entity class field
     */
    public Criteria<T> isEmptyOrNull(String field) {
        Criteria<T> tCriteria = new Criteria<T>()
                .isNull(field)
                .isEmpty(field);
        return this.or(tCriteria);
    }

    /**
     * isEmptyOrNull query
     * example:
     * hql writing hql += "and (id = '' or id is null)"
     * Constructor writing this.isEmptyOrNull(xx::getId);
     *
     * @Param : field entity class field
     */
    public Criteria<T> isEmptyOrNull(SFunction<T, ?> field) {
        return this.isEmptyOrNull(getFieldName(field));
    }

    /**
     * isNotEmpty query
     * example:
     * hql writing hql += "and id != '' "
     * Constructor writing this.isNotEmpty(xx::getId);
     *
     * @Param : field entity class field
     */
    public Criteria<T> isNotEmpty(SFunction<T, ?> field) {
        criterions.add(new SimpleCriterionImpl(getFieldName(field), null, ICriterion.Operator.IS_NOT_EMPTY));
        return this;
    }

    /**
     * isNotEmpty query
     * example:
     * hql writing hql += "and id != '' "
     * Constructor writing this.isNotEmpty("id");
     *
     * @Param : field entity class field
     */
    public Criteria<T> isNotEmpty(String field) {
        criterions.add(new SimpleCriterionImpl(field, null, ICriterion.Operator.IS_NOT_EMPTY));
        return this;
    }

    /**
     * isNotEmptyAndNotNull query
     * example:
     * hql writing hql += "and (id != '' and id is not null)"
     * Constructor writing this.isNotEmptyAndNotNull(xx::getId);
     *
     * @Param : field entity class field
     */
    public Criteria<T> isNotEmptyAndNotNull(SFunction<T, ?> field) {
        String fieldName = getFieldName(field);
        return this.isNotEmptyAndNotNull(fieldName);
    }

    /**
     * isNotEmptyAndNotNull query
     * example:
     * hql writing hql += "and (id != '' and id is not null)"
     * Constructor writing this.isNotEmptyAndNotNull("id");
     *
     * @Param : field entity class field
     */
    public Criteria<T> isNotEmptyAndNotNull(String field) {
        Criteria<T> tCriteria = new Criteria<T>()
                .isNotEmpty(field)
                .isNotNull(field);
        return this.add(tCriteria);
    }

    /**
     * Descending
     * example:
     * hql writing hql += "order by id desc"
     * Constructor writing this.orderByDesc(xx::getId);
     *
     * @Param : field entity class field
     */
    public Criteria<T> orderByDesc(SFunction<T, ?> field) {
        orders.add(new SimpleCriterionImpl(getFieldName(field), null, ICriterion.Operator.ORDER_BY_DESC));
        return this;
    }

    /**
     * Ascending
     * example:
     * hql writing hql += "order by id asc"
     * Constructor writing this.orderByAsc(xx::getId);
     *
     * @Param : field entity class field
     */
    public Criteria<T> orderByAsc(SFunction<T, ?> field) {
        orders.add(new SimpleCriterionImpl(getFieldName(field), null, ICriterion.Operator.ORDER_BY_ASC));
        return this;
    }
}