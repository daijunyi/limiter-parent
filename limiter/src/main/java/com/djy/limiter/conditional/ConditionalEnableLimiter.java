package com.djy.limiter.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * des:
 *
 * @author jun.yi.dai
 * @version : ConditionalEnableLimiter, v 0.1 2023/4/4 16:10 jun.yi.dai Exp $
 */
public class ConditionalEnableLimiter implements Condition {

    protected static Boolean enable = false;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return enable;
    }
}
