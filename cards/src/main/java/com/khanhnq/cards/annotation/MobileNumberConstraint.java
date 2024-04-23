package com.khanhnq.cards.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MobileNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface MobileNumberConstraint {
    String message() default "Invalid mobile number format.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
