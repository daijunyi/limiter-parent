package com.djy.limiter.strategy;

import com.djy.limiter.callback.RetryCallback;
import lombok.Data;
import lombok.Setter;

import java.util.List;

/**
 * des:
 *
 * @author jun.yi.dai
 * @version : AbsStrategy, v 0.1 2023/4/1 11:22 jun.yi.dai Exp $
 */
@Data
public abstract class AbsStrategy implements RetryCallback {
    /**
     * 重试次数
     */
    private int maxRetryCount = 3;

    public void setMaxRetryCount(int maxRetryCount) {
        if (maxRetryCount > 1000){
            throw new IllegalArgumentException("重试的次数，最大不能超过1000次，为了防止死循环无限制的重试");
        }
        this.maxRetryCount = maxRetryCount;
    }

    /**
     * 需要重试的异常列表
     */
    @Setter
    private List<Class<? extends Throwable>> retryExceptionList;

    /**
     * 不需要重试的异常列表
     */
    @Setter
    private List<Class<? extends Throwable>> notRetryExceptionList;

    public AbsStrategy() {
    }

    public AbsStrategy(int maxRetryCount) {
        if (maxRetryCount > 1000){
            throw new IllegalArgumentException("重试的次数，最大不能超过1000次，为了防止死循环无限制的重试");
        }
        this.maxRetryCount = maxRetryCount;
    }

    @Override
    public final boolean onFailIsRetry(int currentRetryCount, Throwable e) {
        if (notRetryExceptionList != null && notRetryExceptionList.contains(e)) {
            return false;
        }
        if (retryExceptionList != null && retryExceptionList.size() > 0) {
            if (retryExceptionList.contains(e)) {
                return toRetry(currentRetryCount);
            }else{
                return false;
            }
        }
        return toRetry(currentRetryCount);
    }

    /**
     * 少于多少次就进行重试 如果重试次数超过100次就不准重试了
     * @param currentRetryCount
     * @return
     */
    protected boolean toRetry(int currentRetryCount) {
        if (currentRetryCount > 100){
            return false;
        }
        if (currentRetryCount <= maxRetryCount) {
            retryStrategy();
            return true;
        }
        return false;
    }

    /**
     * 重试策略
     * @return
     */
    abstract protected void retryStrategy();

}
