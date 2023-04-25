package com.djy.limiter.conditional;

import com.djy.limiter.core.annotation.EnableRetry;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * des: 导入注解配置
 *
 * @author jun.yi.dai
 * @version : EnableLimiterSelector, v 0.1 2023/4/4 16:49 jun.yi.dai Exp $
 */
public class EnableRetrySelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> attributes = importingClassMetadata.getAnnotationAttributes(EnableRetry.class.getName());
        if (attributes != null) {
            ConditionalEnableRetry.enable = (boolean) attributes.get("enable");
        }
        return new String[]{};
    }
}
