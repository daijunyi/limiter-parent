package com.djy.limiter.autoconfigure;


import com.djy.limiter.LimiterTemplate;
import com.djy.limiter.aspect.AspectRateLimiter;
import com.djy.limiter.autoconfigure.abs.AbsPlatformLimiterAutoConfiguration;
import com.djy.limiter.conditional.ConditionalEnableLimiter;
import com.djy.limiter.enums.LimiterEnum;
import com.djy.limiter.exception.LimiterException;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * des: 限流器自动装配，
 *
 *
 * @author jun.yi.dai
 * @version : PlatformLimiterAutoConfiguration, v 0.1 2023/3/30 17:48 jun.yi.dai Exp $
 */
@Configuration
@EnableConfigurationProperties(PlatformLimiterProperties.class)
@AutoConfigureAfter({RedisAutoConfiguration.class})
public class PlatformLimiterAutoConfiguration extends AbsPlatformLimiterAutoConfiguration {

    /**
     * 自动装配限流器
     * @param redisTemplate
     * @param properties
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public LimiterTemplate limiterTemplate(StringRedisTemplate redisTemplate, PlatformLimiterProperties properties){
        String limiterTemplateImpl = properties.getLimiterTemplateImpl();
        try {
            LimiterTemplate limiterTemplate = getLimiterTemplate(limiterTemplateImpl);
            setLimiterCurrentLimiter(properties, limiterTemplate);
            setLimiterCacheDbTemplate(redisTemplate, limiterTemplate);
            return limiterTemplate;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new LimiterException(LimiterEnum.LIMITER_IMPL_ERROR,e);
        }
    }

    /**
     * 注册限流切面代码
     * @param limiterTemplate
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    @Conditional(ConditionalEnableLimiter.class)
    public AspectRateLimiter aspectRateLimiter(LimiterTemplate limiterTemplate){
        AspectRateLimiter aspectRateLimiter = new AspectRateLimiter();
        aspectRateLimiter.setLimiterTemplate(limiterTemplate);
        return aspectRateLimiter;
    }
}
