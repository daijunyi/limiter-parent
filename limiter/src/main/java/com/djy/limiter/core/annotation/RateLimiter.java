package com.djy.limiter.core.annotation;


import com.djy.limiter.Limiter.AbsCurrentLimiter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * des: 限流注解
 *
 * @author jun.yi.dai
 * @version : Limitter, v 0.1 2023/3/30 18:11 jun.yi.dai Exp $
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RateLimiter {

    /**
     * 限流器名称
     * @return
     */
    String key();

    /**
     * 该时间范围最大多少次
     * @return
     */
    int maxInInterval();

    /**
     * 限流时间 默认1秒 单位根据timeUnit 进行设置
     * @return
     */
    long rangeTime() default 1000;

    /**
     * 时间单位 默认毫秒
     * @return
     */
    TimeUnit timeUnit() default MILLISECONDS;

    /**
     * 限流器
     * @return
     */
    Class<? extends AbsCurrentLimiter> currentLimiter() default AbsCurrentLimiter.class;
}
