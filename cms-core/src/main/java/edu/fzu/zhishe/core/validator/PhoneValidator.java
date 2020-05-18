package edu.fzu.zhishe.core.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 验证手机号的注解
 *
 * @author liang
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = PhoneValidatorClass.class)
public @interface PhoneValidator {

    String message() default "phone is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
