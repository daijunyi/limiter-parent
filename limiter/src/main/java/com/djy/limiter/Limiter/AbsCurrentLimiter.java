package com.djy.limiter.Limiter;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.TimeUnit;

/**
 * des:
 *
 * @author jun.yi.dai
 * @version : BaseCurrentLimiter, v 0.1 2023/3/31 17:43 jun.yi.dai Exp $
 */
@Data
@Accessors(chain = true)
public abstract class AbsCurrentLimiter implements CurrentLimiter{

    /**
     * 限流器名称 一般是 前缀:请求IP 例如： rate:limiter:
     */
    protected String key;

    /**
     * 该时间范围最大多少次
     */
    protected Integer maxInInterval = 20;

    /**
     * 限流时间 默认1秒
     */
    protected Long rangeTime = 1000L;

    /**
     * 时间单位 默认毫秒
     */
    protected TimeUnit timeUnit = TimeUnit.MILLISECONDS;

}
