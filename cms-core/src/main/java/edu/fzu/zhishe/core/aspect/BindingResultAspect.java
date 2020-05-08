package edu.fzu.zhishe.core.aspect;

import edu.fzu.zhishe.common.api.AjaxResponse;
import edu.fzu.zhishe.common.api.Error;
import edu.fzu.zhishe.common.api.UnprocessableCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * HibernateValidator 错误结果处理切面
 *
 * @author liang
 */
@Aspect
@Component
@Order(5)
public class BindingResultAspect {

    @Pointcut("execution(public * edu.fzu.zhishe.core.web.*.*(..))")
    public void bindingResult() {
    }

    @Around("bindingResult()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    FieldError fieldError = result.getFieldError();
                    if (fieldError != null) {
                        Error error = new Error("default", fieldError.getField(),
                            UnprocessableCode.INVALID.getCode());
                        return ResponseEntity.unprocessableEntity().body(new AjaxResponse().errors(error));
                    } else {
                        return ResponseEntity.unprocessableEntity();
                    }
                }
            }
        }
        return joinPoint.proceed();
    }
}
