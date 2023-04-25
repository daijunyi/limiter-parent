package com.djy.limiter.impl;

import com.djy.limiter.Limiter.CurrentLimiter;
import com.djy.limiter.Limiter.RedisCurrentLimiter;
import com.djy.limiter.Limiter.impl.SlideTimeScriptWindow;
import com.djy.limiter.LimiterTemplate;
import com.djy.limiter.enums.LimiterEnum;
import com.djy.limiter.exception.LimiterException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * des: 限流具体实现模版方法
 *
 * @author jun.yi.dai
 * @version : LimiterTemplateImpl, v 0.1 2023/3/29 17:51 jun.yi.dai Exp $
 */
@Slf4j
public class LimiterTemplateImpl extends BaseRedisLimiterTemplate implements LimiterTemplate {

    /**
     * 每秒限流多少
     * @param key
     * @param tps
     */
    @Override
    public void limitSeconds(String key, Integer tps) throws LimiterException {
        limit(key,tps,1L, TimeUnit.SECONDS);
    }

    @Override
    public void limit(String key, Integer maxInInterval, Long rangeTime, TimeUnit timeUnit) throws LimiterException {
        if (absCurrentLimiter != null){
            absCurrentLimiter.setMaxInInterval(maxInInterval);
            absCurrentLimiter.setKey(key);
            absCurrentLimiter.setRangeTime(rangeTime);
            absCurrentLimiter.setTimeUnit(timeUnit);
            setCheckParams(absCurrentLimiter);
            limit(absCurrentLimiter);
        }else{
            SlideTimeScriptWindow slideTimeScriptWindow = new SlideTimeScriptWindow();
            slideTimeScriptWindow.setKey(key);
            slideTimeScriptWindow.setMaxInInterval(maxInInterval);
            slideTimeScriptWindow.setRangeTime(rangeTime);
            slideTimeScriptWindow.setTimeUnit(timeUnit);
            setCheckParams(slideTimeScriptWindow);
            limit(slideTimeScriptWindow);
        }
    }

    /**
     * 限流根据限流器
     * @param check
     */
    @Override
    public void limit(CurrentLimiter check) throws LimiterException {
        setCheckParams(check);
        if (!isLimit(check)){
        } else {
            throw new LimiterException(LimiterEnum.LIMITER);

        }
    }

    /**
     * 设置限流器相关参数 比如设置redisTemplate
     * @param check
     */
    private void setCheckParams(CurrentLimiter check) {
        //如果是redis的限流器设置redisTemplate
        if (check instanceof RedisCurrentLimiter){
            RedisCurrentLimiter redisCurrentLimiter = (RedisCurrentLimiter) check;
            if (redisCurrentLimiter.getStringRedisTemplate() == null){
                redisCurrentLimiter.setStringRedisTemplate(stringRedisTemplate);
            }
        }
    }


    /**
     *
     * @param limiter
     * @return
     */
    private boolean isLimit(CurrentLimiter limiter) {
        try {
            return limiter.limit();
        } catch (Throwable e) {
            throw new LimiterException(LimiterEnum.LIMITER_ERROR, e);
        }
    }
}
