package com.example.campus_connect.DTOs;

import java.util.HashSet;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;

public class ValidEmailDomainValidator implements ConstraintValidator<ValidEmailDomain, String> {

    private Set<String> allowedDomains;

    @Override
    public void initialize(ValidEmailDomain constraintAnnotation) {
        allowedDomains = new HashSet<>(Arrays.asList(constraintAnnotation.allowedDomains()));
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || !email.contains("@")) return false;

        String domain = email.substring(email.indexOf("@") + 1);
        return allowedDomains.contains(domain);
    }
}