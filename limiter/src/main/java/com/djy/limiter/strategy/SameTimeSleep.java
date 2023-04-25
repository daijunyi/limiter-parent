package com.djy.limiter.strategy;

import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * des: 相同时间 重试策略 睡眠
 *
 * @author jun.yi.dai
 * @version : IncreaseSleep, v 0.1 2023/4/2 23:01 jun.yi.dai Exp $
 */
@Accessors(chain = true)
public class SameTimeSleep extends AbsStrategy{

    @Setter
    private Long sleepTime;

    public SameTimeSleep() {
    }

    /**
     * 默认睡眠时间
     * @param maxRetryCount 最大重试次数
     */
    public SameTimeSleep(int maxRetryCount) {
        super(maxRetryCount);
    }

    /**
     * 默认睡眠时间
     * @param maxRetryCount
     * @param sleepTime
     */
    public SameTimeSleep(int maxRetryCount, Long sleepTime) {
        super(maxRetryCount);
        this.sleepTime = sleepTime;
    }

    /**
     * 重试策略 睡眠
     */
    @Override
    protected void retryStrategy() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
