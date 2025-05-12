package com.example.campus_connect.DTOs;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidEmailDomainValidator.class)
@Documented
public @interface ValidEmailDomain {
    String message() default "Email must be a university email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] allowedDomains();
}