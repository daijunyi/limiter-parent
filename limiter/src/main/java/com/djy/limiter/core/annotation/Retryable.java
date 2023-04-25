package com.djy.limiter.core.annotation;


import com.djy.limiter.strategy.AbsStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * des: 如果执行失败，将进行重试。
 *
 * @author jun.yi.dai
 * @version : Retryable, v 0.1 2023/4/2 23:24 jun.yi.dai Exp $
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Retryable {

    /**
     * 最大重试次数
     * @return
     */
    int maxRetryCount() default 3;

    /**
     * 重试策略 默认直接重试
     * @return
     */
    Class<? extends AbsStrategy> strategy() default AbsStrategy.class;

    /**
     * 重试异常 如果设置了将只会在这些异常中进行重试
     * @return
     */
    Class<? extends Throwable>[] retryExceptions() default {};

    /**
     * 不重试异常 如果设置了该值，将排查这些异常，不进行重试
     * @return
     */
    Class<? extends Throwable>[] noRetryExceptions() default {};

}
