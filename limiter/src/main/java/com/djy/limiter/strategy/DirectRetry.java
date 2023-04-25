package com.djy.limiter.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * des: 直接重试 没有那么多花里胡哨的
 *
 * @author jun.yi.dai
 * @version : DirectRetry, v 0.1 2023/4/2 23:50 jun.yi.dai Exp $
 */
@Slf4j
public class DirectRetry extends AbsStrategy{

    public DirectRetry() {
    }

    public DirectRetry(int maxRetryCount) {
        super(maxRetryCount);
    }

    @Override
    protected void retryStrategy() {
        log.info("直接重试");
    }
}
