package com.djy.limiter.core.annotation;


import com.djy.limiter.conditional.EnableRetrySelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * des: 是否开启重试注解功能，不配置该注解，重试的注解不能使用
 *
 * @author jun.yi.dai
 * @version : Limitter, v 0.1 2023/3/30 18:11 jun.yi.dai Exp $
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(EnableRetrySelector.class)
public @interface EnableRetry {

    /**
     * 是否开启重试，默认是重试注解功能
     * @return
     */
    boolean enable() default true;
}
