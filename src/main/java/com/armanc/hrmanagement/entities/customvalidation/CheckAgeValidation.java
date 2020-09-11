package com.armanc.hrmanagement.entities.customvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

@Constraint(validatedBy = checkAgeConstraintValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAgeValidation {

    public int value() default 18;

    public String message() default "You must be 18 or older";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


//    LocalDate limitDate = LocalDate.now().minusYears(ageLimit);
//    int limitMonth = limitDate.getMonthValue();
//    int limitDay = limitDate.getDayOfMonth();
//
//        if (birthDate.compareTo(limitDate) == ageLimit) {
//                if (birthDate.getMonthValue() == limitMonth) {
//                return birthDate.getDayOfMonth() < limitDay;
//            } else return limitMonth > birthDate.getMonthValue();
//                    } else return limitDate.compareTo(birthDate) > 0;