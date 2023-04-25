package com.djy.limiter.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * des:
 *
 * @author jun.yi.dai
 * @version : PlatformLimiterProperties, v 0.1 2023/3/30 17:52 jun.yi.dai Exp $
 */

@Data
@ConfigurationProperties(prefix = "xddigital.platform.limiter")
public class PlatformLimiterProperties {

    /**
     * 限流器
     */
    private String currentLimiter = "com.xddigital.platform.limiter.template.Limiter.impl.SlideTimeScriptWindow";

    /**
     * 限流具体实现类
     */
    private String limiterTemplateImpl = "com.xddigital.platform.limiter.template.impl.LimiterTemplateImpl";
}
