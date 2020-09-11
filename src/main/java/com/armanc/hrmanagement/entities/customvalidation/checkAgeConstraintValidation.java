package com.armanc.hrmanagement.entities.customvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class checkAgeConstraintValidation implements ConstraintValidator<CheckAgeValidation, LocalDate> {

    private int ageLimit;


    public void initialize(CheckAgeValidation constraint) {
        ageLimit = constraint.value();
    }

    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {

        LocalDate limitDate = LocalDate.now().minusYears(ageLimit);
        int limitMonth = limitDate.getMonthValue();
        int limitDay = limitDate.getDayOfMonth();

        if (birthDate.compareTo(limitDate) == ageLimit) {
            if (birthDate.getMonthValue() == limitMonth) {
                return birthDate.getDayOfMonth() <= limitDay;
            } else return limitMonth > birthDate.getMonthValue();
        } else return limitDate.compareTo(birthDate) > 0;

    }
}
