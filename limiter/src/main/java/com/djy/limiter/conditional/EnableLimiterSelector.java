package com.djy.limiter.conditional;

import com.djy.limiter.core.annotation.EnableLimiter;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * des: 导入注解配置
 *
 * @author jun.yi.dai
 * @version : EnableLimiterSelector, v 0.1 2023/4/4 16:49 jun.yi.dai Exp $
 */
public class EnableLimiterSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> attributes = importingClassMetadata.getAnnotationAttributes(EnableLimiter.class.getName());
        if (attributes != null) {
            ConditionalEnableLimiter.enable = (boolean) attributes.get("enable");
        }
        return new String[]{};
    }
}
