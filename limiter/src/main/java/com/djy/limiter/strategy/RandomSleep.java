package com.djy.limiter.strategy;

import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * des: 随机睡眠 在一个范围内
 *
 * @author jun.yi.dai
 * @version : RandomSleep, v 0.1 2023/4/1 11:13 jun.yi.dai Exp $
 */
@Accessors(chain = true)
public class RandomSleep extends AbsStrategy {

    /**
     * 最小睡眠时间
     */
    @Setter
    private Long minSleepTime = 1000L;

    /**
     * 最大睡眠时间
     */
    @Setter
    private Long maxSleepTime = 10000L;

    public RandomSleep() {
    }

    public RandomSleep(int maxRetryCount) {
        super(maxRetryCount);
    }

    /**
     * 随机睡眠
     * @param maxRetryCount 最大重试次数
     * @param minSleepTime 最小睡眠时间
     * @param maxSleepTime 最大睡眠时间
     */
    public RandomSleep(int maxRetryCount, Long minSleepTime, Long maxSleepTime) {
        super(maxRetryCount);
        this.minSleepTime = minSleepTime;
        this.maxSleepTime = maxSleepTime;
    }


    /**
     * 重试策略 随机睡眠
     */
    @Override
    protected void retryStrategy() {
        try {
            Thread.sleep((long) (Math.random() * (maxSleepTime - minSleepTime) + minSleepTime));
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
