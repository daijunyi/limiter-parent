package com.djy.limiter.impl;

import lombok.Data;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * des:
 *
 * @author jun.yi.dai
 * @version : BaseRedisLimiterTemplate, v 0.1 2023/3/30 18:02 jun.yi.dai Exp $
 */
@Data
public abstract class BaseRedisLimiterTemplate extends AbsLimiterTemplate{

    protected StringRedisTemplate stringRedisTemplate;

}
