package com.djy.limiter.impl;

import com.djy.limiter.Limiter.AbsCurrentLimiter;
import lombok.Data;

/**
 * des:
 *
 * @author jun.yi.dai
 * @version : AbsLimiterTemplate, v 0.1 2023/4/1 10:26 jun.yi.dai Exp $
 */
@Data
public class AbsLimiterTemplate {

    protected AbsCurrentLimiter absCurrentLimiter;

}
