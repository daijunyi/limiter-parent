package com.djy.limiter.autoconfigure;

import com.djy.limiter.RetryTemplate;
import com.djy.limiter.aspect.AspectRetryable;
import com.djy.limiter.conditional.ConditionalEnableRetry;
import com.djy.limiter.impl.RetryTemplateImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * des: 重试器
 *
 * @author jun.yi.dai
 * @version : PlatformRetryAutoConfiguartion, v 0.1 2023/4/2 23:31 jun.yi.dai Exp $
 */
@Configuration
public class PlatformRetryAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RetryTemplate retryTemplate(){
        RetryTemplateImpl<Object> objectRetryTemplate = new RetryTemplateImpl<>();
        return objectRetryTemplate;
    }


    @Bean
    @ConditionalOnMissingBean
    @Conditional(ConditionalEnableRetry.class)
    public AspectRetryable aspectRetryable(RetryTemplate<Object> retryTemplate){
        return new AspectRetryable(retryTemplate);
    }

}
