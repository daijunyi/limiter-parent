package com.djy.limiter.strategy;

import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * des: 随机递增时间 重试策略 睡眠
 *
 * @author jun.yi.dai
 * @version : RandomIncreaseSleep, v 0.1 2023/4/2 23:13 jun.yi.dai Exp $
 */
@Accessors(chain = true)
public class RandomIncreaseSleep extends AbsStrategy{

    @Setter
    private Long minSleepTime = 1000L;

    @Setter
    private Long minIncrementalSleepTime = 1000L;

    @Setter
    private Long maxIncrementalSleepTime = 10000L;

    private Long currentSleepTime;

    public RandomIncreaseSleep() {
    }

    /**
     * 随机递增时间
     * @param maxRetryCount 最大重试次数
     * @param minSleepTime 最小睡眠时间
     * @param minIncrementalSleepTime 最小递增睡眠时间
     * @param maxIncrementalSleepTime 最大递增睡眠时间
     */
    public RandomIncreaseSleep(int maxRetryCount, Long minSleepTime, Long minIncrementalSleepTime, Long maxIncrementalSleepTime) {
        super(maxRetryCount);
        this.minSleepTime = minSleepTime;
        this.minIncrementalSleepTime = minIncrementalSleepTime;
        this.maxIncrementalSleepTime = maxIncrementalSleepTime;
    }

    public RandomIncreaseSleep(int maxRetryCount) {
        super(maxRetryCount);
    }

    @Override
    protected void retryStrategy() {
        try {
            if (currentSleepTime == null) {
                currentSleepTime = minSleepTime;
            } else {
                currentSleepTime += (long) (Math.random() * (maxIncrementalSleepTime - minIncrementalSleepTime) + minIncrementalSleepTime);
            }
            Thread.sleep(currentSleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
