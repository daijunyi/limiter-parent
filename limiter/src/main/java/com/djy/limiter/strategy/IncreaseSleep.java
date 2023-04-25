package com.djy.limiter.strategy;

import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * des: 递增睡眠时间
 *
 * @author jun.yi.dai
 * @version : IncreaseSleep, v 0.1 2023/4/2 23:07 jun.yi.dai Exp $
 */
@Accessors(chain = true)
public class IncreaseSleep extends AbsStrategy {

    @Setter
    private Long minSleepTime = 1000L;

    @Setter
    private Long incrementalSleepTime = 1000L;

    /**
     * 当前睡眠时间
     */
    private Long currentSleepTime;

    public IncreaseSleep(){

    }

    public IncreaseSleep(int maxRetryCount) {
        super(maxRetryCount);
    }

    /**
     * 递增睡眠时间
     * @param maxRetryCount 最大重试次数
     * @param minSleepTime 最小睡眠时间
     * @param incrementalSleepTime 递增睡眠时间
     */
    public IncreaseSleep(int maxRetryCount, Long minSleepTime, Long incrementalSleepTime) {
        super(maxRetryCount);
        this.minSleepTime = minSleepTime;
        this.incrementalSleepTime = incrementalSleepTime;
    }

    /**
     * 重试策略 睡眠
     */
    @Override
    protected void retryStrategy() {
        try {
            if (currentSleepTime == null) {
                currentSleepTime = minSleepTime;
            } else {
                currentSleepTime += incrementalSleepTime;
            }
            Thread.sleep(currentSleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
