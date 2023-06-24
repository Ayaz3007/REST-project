package ru.itis.javalab.validation.constraints;

import ru.itis.javalab.validation.validators.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface HardPassword {
    String message() default "{easyPassword}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
