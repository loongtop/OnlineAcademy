package com.gkhy.servicebase.utils.hibernate.criterion;

import javax.persistence.criteria.*;

/**
 * @Name: Criterion
 * @Description:
 * @Author: leo
 * @Created: 2022-09-18
 * @Updated: 2022-09-18
 * @Version: 1.0
 **/
public interface ICriterion {

    // Here is an enumeration of some common operations
    enum Operator {
        EQ, NE, LIKE,LEFT_LIKE,RIGHT_LIKE, GT, LT, GTE, LTE, AND, OR, IN, NOTIN, ISNULL, IS_NOTNULL, ORDER_BY_DESC, ORDER_BY_ASC, IS_EMPTY, IS_NOT_EMPTY
    }

    /**
     * Conditional query interface function
     */
    Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder);

    /**
     * Sort expression interface function
     */
    Order toOrder(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder);
}
