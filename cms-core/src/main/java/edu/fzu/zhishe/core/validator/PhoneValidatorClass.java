package edu.fzu.zhishe.core.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 状态标记校验器
 *
 * @author liang
 */
public class PhoneValidatorClass implements ConstraintValidator<PhoneValidator, String> {

    public static final int PHONE_SIZE = 11;

    @Override
    public void initialize(PhoneValidator phoneValidator) { }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        String regex = "^((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(166)|(17[0135678])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (value.length() != PHONE_SIZE) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(value);
            return m.matches();
        }
    }
}
