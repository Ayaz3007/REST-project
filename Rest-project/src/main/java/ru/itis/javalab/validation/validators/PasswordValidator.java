package ru.itis.javalab.validation.validators;

import org.springframework.data.util.ReflectionUtils;
import ru.itis.javalab.validation.constraints.HardPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<HardPassword, Object> {
    @Override
    public void initialize(HardPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object password, ConstraintValidatorContext constraintValidatorContext) {


        Pattern patternChar = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        Matcher matcherChar = patternChar.matcher((String)password);


        return matcherChar.matches();
    }
}
