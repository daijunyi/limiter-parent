package com.djy.limiter;


import com.djy.limiter.Limiter.CurrentLimiter;
import com.djy.limiter.exception.LimiterException;

import java.util.concurrent.TimeUnit;

/**
 * des: 限流模板
 *
 * @author jun.yi.dai
 * @version : LimiterTempalate, v 0.1 2023/3/29 17:24 jun.yi.dai Exp $
 */
public interface LimiterTemplate {

    /**
     * 每秒限流多少次
     * 如果被限流会抛出异常。
     * @param key 限流器名称
     * @param tps 每秒限流多少次
     */
    void limitSeconds(String key, Integer tps) throws LimiterException;

    /**
     * 根据时间单位限流
     * @param key 限流器名称
     * @param maxInInterval 该时间范围最大多少次
     * @param rangeTime 限流时间
     * @param timeUnit 时间单位
     * @throws LimiterException
     */
    void limit(String key, Integer maxInInterval, Long rangeTime, TimeUnit timeUnit) throws LimiterException;

    /**
     * 限流根据限流器
     * @param check 限流器
     */
    void limit(CurrentLimiter check) throws LimiterException;
}
