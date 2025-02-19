package io.github.victor_vn.request_scope_bean_poc.annotation;

import io.github.victor_vn.request_scope_bean_poc.domain.ProcessGlobalDto;
import io.github.victor_vn.request_scope_bean_poc.feign.PlaceholderFeignClient;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Aspect
@Component
public class LoggingAspect {

    private PlaceholderFeignClient feignClient;
    private ProcessGlobalDto processGlobalDto;

    public LoggingAspect(PlaceholderFeignClient feignClient, ProcessGlobalDto processGlobalDto) {
        this.feignClient = feignClient;
        this.processGlobalDto = processGlobalDto;
    }

    @Before("@within(io.github.victor_vn.request_scope_bean_poc.annotation.EnablePrePosHandling) || @annotation(io.github.victor_vn.request_scope_bean_poc.annotation.EnablePrePosHandling)")
    public void handlePreAction(JoinPoint joinPoint) {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        Method method = getMethodFromJoinPoint(joinPoint);

        EnablePrePosHandling annotation = targetClass.getAnnotation(EnablePrePosHandling.class);
        if (annotation == null) {
            annotation = method.getAnnotation(EnablePrePosHandling.class);
        }

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        System.out.println("Initializing process DTO");
        processGlobalDto.setInput(request.getParameter("value"));

        if (annotation != null) {
            System.out.println("Fetching external system");
            processGlobalDto.setUserData(feignClient.getUserInfo(Integer.parseInt(request.getParameter("value"))).getTitle());

            System.out.println("Ending PreAction userData: " + processGlobalDto.getUserData());
        }
    }

    @After("@within(io.github.victor_vn.request_scope_bean_poc.annotation.EnablePrePosHandling) || @annotation(io.github.victor_vn.request_scope_bean_poc.annotation.EnablePrePosHandling)")
    public void handlePostAction(JoinPoint joinPoint) {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        EnablePrePosHandling annotation = targetClass.getAnnotation(EnablePrePosHandling.class);
        if (annotation != null) {
            System.out.println("Ending PostAction - OutputAfterTransformation: " + processGlobalDto.getOutput());
        }
    }

    private Method getMethodFromJoinPoint(JoinPoint joinPoint) {
        Method method = null;
        try {
            String methodName = joinPoint.getSignature().getName();
            Class<?>[] parameterTypes = ((org.aspectj.lang.reflect.MethodSignature) joinPoint.getSignature()).getParameterTypes();
            method = joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }
}