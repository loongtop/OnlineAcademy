package com.gkhy.servicebase.utils.hibernate.criterion.impl;

import com.gkhy.servicebase.utils.hibernate.criterion.ICriterion;

import javax.persistence.criteria.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Name: CriterionComplexImpl
 * @Description:
 * @Author: leo
 * @Created: 2022-09-18
 * @Updated: 2022-09-18
 * @Version: 1.0
 **/

public class CriterionComplexImpl implements ICriterion {

    /**
     * 多条件表达式
     */
    private final List<ICriterion> criterionList;

    /**
     * 操作符
     */
    private final Operator operator;

    public CriterionComplexImpl(List<ICriterion> criterionList, Operator operator) {
        this.criterionList = criterionList;
        this.operator = operator;
    }

    @Override
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = criterionList.stream().map(x -> x.toPredicate(root, query, builder)).collect(Collectors.toList());
        switch (operator) {
            case OR:
                return builder.or(predicates.toArray(new Predicate[0]));
            case AND:
                return builder.and(predicates.toArray(new Predicate[0]));
            default:
                return null;
        }
    }

    @Override
    public Order toOrder(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        // 在这个表达式中不应该出现排序
        return null;
    }
}
