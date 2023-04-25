package com.djy.limiter.callback;

/**
 * des:
 *
 * @author jun.yi.dai
 * @version : RetryCallback, v 0.1 2023/4/1 11:04 jun.yi.dai Exp $
 */
public interface RetryCallback {

    /**
     * 当失败的时候回调
     * @param currentRetryCount
     * @return 是否重试 true 重试 false 不重试
     * @throws Exception
     */
    boolean onFailIsRetry(int currentRetryCount, Throwable e);

}
