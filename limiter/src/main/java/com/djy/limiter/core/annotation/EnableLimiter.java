package com.djy.limiter.core.annotation;

import com.djy.limiter.conditional.EnableLimiterSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * des: 是否开启限流注解功能,不配置该注解，注解的限流不能使用
 *
 * @author jun.yi.dai
 * @version : Limitter, v 0.1 2023/3/30 18:11 jun.yi.dai Exp $
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(EnableLimiterSelector.class)
public @interface EnableLimiter {

    /**
     * 是否开启限流，默认是限流开启
     * @return
     */
    boolean enable() default true;
}
