package com.djy.limiter.Limiter;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * des:
 *
 * @author jun.yi.dai
 * @version : RedisCheck, v 0.1 2023/3/29 22:30 jun.yi.dai Exp $
 */
@Data
@Accessors(chain = true)
public abstract class RedisCurrentLimiter extends AbsCurrentLimiter {

    protected StringRedisTemplate stringRedisTemplate;

}
