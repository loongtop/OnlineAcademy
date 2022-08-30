package com.gkhy.commonutils.constraints;

import com.gkhy.commonutils.constraints.annotation.ListValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;


public class ListConstraintsValidator implements ConstraintValidator<ListValue,Integer> {

    private final Set<Integer> integerHashSet = new HashSet<>();

    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] value = constraintAnnotation.value();
        for (int i : value) {
            integerHashSet.add(i);
        }

    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        return  integerHashSet.contains(value);
    }
}
