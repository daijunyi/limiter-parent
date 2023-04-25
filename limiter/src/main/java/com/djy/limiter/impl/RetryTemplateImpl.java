package com.djy.limiter.impl;

import com.djy.limiter.RetryTemplate;
import com.djy.limiter.callback.FailCallBack;
import com.djy.limiter.callback.RetryCallback;
import com.djy.limiter.callback.RunCallback;
import lombok.extern.slf4j.Slf4j;

/**
 * des:
 *
 * @author jun.yi.dai
 * @version : RetryTemplateImpl, v 0.1 2023/4/1 11:09 jun.yi.dai Exp $
 */
@Slf4j
public class RetryTemplateImpl<T> implements RetryTemplate<T> {

    @Override
    public <T> T retry(RetryCallback retryCallback, RunCallback<T> runCallback) throws Throwable {
        int currentRetryCount = 0;
        while (true){
            try {
                log.info("重试了次数:{}",currentRetryCount);
                return runCallback.run();
            } catch (Throwable e){
                currentRetryCount++;
                if (retryCallback.onFailIsRetry(currentRetryCount, e)){
                    continue;
                }
                throw e;
            }
        }
    }

    /**
     * 重试，当多次重试失败的时候回调，自己去处理异常情况
     * @param retryCallback
     * @param runnable
     * @param failCallBack
     * @return
     * @param <T>
     */
    @Override
    public <T> T retry(RetryCallback retryCallback, RunCallback<T> runnable, FailCallBack failCallBack) {
        int currentRetryCount = 0;
        while (true){
            try {
                log.info("重试了次数:{}",currentRetryCount);
                return runnable.run();
            } catch (Throwable e){
                currentRetryCount++;
                if (retryCallback.onFailIsRetry(currentRetryCount, e)){
                    continue;
                }
                failCallBack.onFail(e);
            }
        }
    }
}
