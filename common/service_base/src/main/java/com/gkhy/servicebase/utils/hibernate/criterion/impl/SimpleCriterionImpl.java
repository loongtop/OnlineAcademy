package com.gkhy.servicebase.utils.hibernate.criterion.impl;

import com.gkhy.servicebase.utils.hibernate.criterion.ICriterion;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.*;
import java.util.Collection;

/**
 * @Name: SimpleCriterionImpl
 * @Description:
 * @Author: leo
 * @Created: 2022-09-18
 * @Updated: 2022-09-18
 * @Version: 1.0
 **/

public class SimpleCriterionImpl implements ICriterion {
    /**
     *  属性名
     */
    private final String fieldName;
    /**
     *  对应值
     */
    private final Object value;
    /**
     * 计算符
     */
    private final Operator operator;

    public SimpleCriterionImpl(String fieldName, Object value, Operator operator) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Predicate toPredicate(Root<?> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {

        Path expression = this.obtainPath(root);
        switch (operator) {
            case EQ:
                return builder.equal(expression, value);
            case NE:
                return builder.notEqual(expression, value);
            case LIKE:
                return builder.like((Expression<String>) expression, "%" + value + "%");
            case LEFT_LIKE:
                return builder.like((Expression<String>) expression, "%" + value);
            case RIGHT_LIKE:
                return builder.like((Expression<String>) expression, value + "%");
            case LT:
                return builder.lessThan(expression, (Comparable) value);
            case GT:
                return builder.greaterThan(expression, (Comparable) value);
            case LTE:
                return builder.lessThanOrEqualTo(expression, (Comparable) value);
            case GTE:
                return builder.greaterThanOrEqualTo(expression, (Comparable) value);
            case IN:
                if (value instanceof Collection){
                    CriteriaBuilder.In in = builder.in(expression);
                    ((Collection<?>) value).forEach(x->in.value(String.valueOf(x)));
                    return in;
                }
                throw new RuntimeException("无法处理非数组类型的数据");
            case NOTIN:
                if (value instanceof Collection){
                    CriteriaBuilder.In in = builder.in(expression);
                    ((Collection<?>) value).forEach(x->in.value(String.valueOf(x)));
                    return builder.not(in);
                }
                throw new RuntimeException("无法处理非数组类型的数据");
            case ISNULL:
                return builder.isNull(expression);
            case IS_NOTNULL:
                return builder.isNotNull(expression);
            case IS_EMPTY:
                return builder.equal(expression,"");
            case IS_NOT_EMPTY:
                return builder.notEqual(expression,"");
            default:
                return null;
        }
    }

    @Override
    public Order toOrder(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path expression = this.obtainPath(root);
        switch (operator){
            case ORDER_BY_DESC:
                return builder.desc(expression);
            case ORDER_BY_ASC:
                return builder.asc(expression);
            default:
                return null;
        }
    }

    /**
     * 获取实体属性
     *
     * @param root
     * @return
     */
    private Path obtainPath(Root<?> root){
        Path expression;
        // 此处是表关联数据，现在想怎么关联就怎么关联，为爽而开发
        // 例如 product查询父级品牌 .eq(parent.brand.nameCn."x")
        // 查询 product的 parent 的 brand 的 nameCn 为 x
        if (fieldName.contains(".")) {
            String[] names = StringUtils.split(fieldName, ".");
            // 先关联第一个属性字段
            Join join = root.join(names[0], JoinType.LEFT);
            // 循环到第n-1个属性字段
            int namesLength = names.length - 1;
            for (int i = 1; i < namesLength; i++) {
                join = join.join(names[i], JoinType.LEFT);
            }
            // 末尾获取属性路径
            expression = join.get(names[namesLength]);
        } else {
            //单表查询
            expression = root.get(fieldName);
        }
        return expression;
    }
}
