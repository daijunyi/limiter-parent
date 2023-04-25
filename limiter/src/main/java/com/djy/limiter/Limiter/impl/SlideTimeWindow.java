package com.djy.limiter.Limiter.impl;


import com.djy.limiter.Limiter.RedisCurrentLimiter;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;

/**
 * des: 滑动时间窗口算法
 *
 * @author jun.yi.dai
 * @version : Time, v 0.1 2023/3/29 19:39 jun.yi.dai Exp $
 */
@Slf4j
@Data
@Accessors(chain = true)
public class SlideTimeWindow extends RedisCurrentLimiter {

    /**
     * 时间活动缓存提前删除的毫秒范围值（毫秒）
     */
    private Long interval = 3000L;

    @Override
    public boolean limit() throws Throwable{
        int limit = this.maxInInterval;
        String key = this.key;
        Long currentTime = System.currentTimeMillis();
        try {
            Long count = stringRedisTemplate.opsForZSet().count(key, currentTime - timeUnit.toMillis(rangeTime), currentTime);
            if (count != null && count >= limit) {
                return true;
            }
            stringRedisTemplate.opsForZSet().add(key, java.util.UUID.randomUUID().toString(),currentTime);
        } catch (RedisConnectionFailureException e) {
            throw e;
        }finally {
            // 三秒接口缓冲区，防止redis命令乱序导致的访问量统计错误（如果限流量和实际系统支持量差距很大，则会出很多问题，可调节缓冲时间来降低乱序导致的访问量计数错误）
            stringRedisTemplate.opsForZSet().removeRangeByScore(key, 0, currentTime - timeUnit.toMillis(rangeTime)- interval);
        }
        return false;
    }

}
