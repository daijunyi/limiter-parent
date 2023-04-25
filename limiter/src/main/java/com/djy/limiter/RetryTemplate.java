package com.djy.limiter;


import com.djy.limiter.callback.FailCallBack;
import com.djy.limiter.callback.RetryCallback;
import com.djy.limiter.callback.RunCallback;

/**
 * des: 重试模版
 *
 * @author jun.yi.dai
 * @version : RetryTemplate, v 0.1 2023/4/1 11:01 jun.yi.dai Exp $
 */
public interface RetryTemplate<T> {

    /**
     * 重试
     * @param runnable
     * @param retryCallback
     * @param <T>
     * @return
     */
    <T> T retry(RetryCallback retryCallback, RunCallback<T> runnable) throws Throwable;


    /**
     * 重试
     * @param retryCallback
     * @param runnable
     * @param failCallBack
     * @return
     * @param <T>
     */
    <T> T retry(RetryCallback retryCallback, RunCallback<T> runnable, FailCallBack failCallBack);
}
