package com.khanhnq.accounts.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class MobileNumberValidator implements ConstraintValidator<MobileNumberConstraint, String> {
    @Override
    public void initialize(MobileNumberConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String mobileNumber, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;
        if (Objects.isNull(mobileNumber) || !mobileNumber.matches("^0[2|3|5|7|8|9][0-9]{8}$")) {
            isValid = false;
        }
        return isValid;
    }
}
