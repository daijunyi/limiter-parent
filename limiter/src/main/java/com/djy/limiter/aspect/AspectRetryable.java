package com.djy.limiter.aspect;


import com.djy.limiter.RetryTemplate;
import com.djy.limiter.aspect.base.BaseAspect;
import com.djy.limiter.callback.RunCallback;
import com.djy.limiter.core.annotation.Retryable;
import com.djy.limiter.strategy.AbsStrategy;
import com.djy.limiter.strategy.DirectRetry;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * des: 重试
 *
 * @author jun.yi.dai
 * @version : AspectRetryable, v 0.1 2023/4/2 23:34 jun.yi.dai Exp $
 */

@Order(-2)
@Aspect
@Slf4j
public class AspectRetryable extends BaseAspect {

    @Getter
    @Setter
    private RetryTemplate retryTemplate;

    public AspectRetryable() {
    }

    public AspectRetryable(RetryTemplate retryTemplate) {
        this.retryTemplate = retryTemplate;
    }

    /**
     * 拦截： com.xddigital.platform.limiter.template.core.annotation.Retryable
     */
    @Pointcut("@annotation(com.djy.limiter.core.annotation.Retryable)")
    public void annotationCut() {}

    @Around("annotationCut()")
    public Object annotationCut(ProceedingJoinPoint pjp) throws Throwable {
        return around(pjp);
    }

    private Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Retryable targetMethodAnnotation = getTargetMethodAnnotation(joinPoint, Retryable.class);
        Class<? extends AbsStrategy> strategy = targetMethodAnnotation.strategy();
        AbsStrategy absStrategy = null;
        int maxRetryCount = targetMethodAnnotation.maxRetryCount();
        if (strategy == AbsStrategy.class) {
            absStrategy = new DirectRetry(maxRetryCount);
        }else {
            absStrategy = strategy.newInstance();
            absStrategy.setMaxRetryCount(maxRetryCount);
        }
        Class<? extends Throwable>[] retryExceptions = targetMethodAnnotation.retryExceptions();
        List<Class<? extends Throwable>> retryExceptionsList = Arrays.stream(retryExceptions).collect(Collectors.toList());
        absStrategy.setRetryExceptionList(retryExceptionsList);
        Class<? extends Throwable>[] noRetryExceptions = targetMethodAnnotation.noRetryExceptions();
        List<Class<? extends Throwable>> noRetryExceptionsList = Arrays.stream(noRetryExceptions).collect(Collectors.toList());
        absStrategy.setNotRetryExceptionList(noRetryExceptionsList);
        return retryTemplate.retry(absStrategy, new RunCallback<Object>() {
            @Override
            public Object run() throws Throwable {
                try {
                    return joinPoint.proceed();
                } catch (Throwable throwable) {
                    throw throwable;
                }
            }
        });
    }
}
