package com.djy.limiter.aspect.base;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * des:
 *
 * @author jun.yi.dai
 * @version : BaseAspect, v 0.1 2023/4/2 23:40 jun.yi.dai Exp $
 */
public abstract class BaseAspect {

    /**
     * 获取注解 目标方法上的注解
     * @param joinPoint
     * @param annotationClass
     * @return
     * @param <T>
     * @throws NoSuchMethodException
     */
    protected  <T extends Annotation> T getTargetMethodAnnotation(ProceedingJoinPoint joinPoint, Class<T> annotationClass) throws NoSuchMethodException {
        //目的：获取切入点方法上自定义RequiredLog注解中operation属性值
        //1.1获取目标对象对应的字节码对象
        Class<?> targetCls= joinPoint.getTarget().getClass();
        //1.2获取目标方法对象
        //1.2.1 获取方法签名信息从而获取方法名和参数类型
        Signature signature= joinPoint.getSignature();
        //1.2.1.1将方法签名强转成MethodSignature类型，方便调用
        MethodSignature ms= (MethodSignature)signature;
        //1.2.2通过字节码对象以及方法签名获取目标方法对象
        Method targetMethod=targetCls.getDeclaredMethod(ms.getName(),ms.getParameterTypes());
        //1.3获取目标方法对象上注解中的属性值
        //1.2.3 获取方法上的自定义requiredLog注解
        return targetMethod.getAnnotation(annotationClass);
    }

}
