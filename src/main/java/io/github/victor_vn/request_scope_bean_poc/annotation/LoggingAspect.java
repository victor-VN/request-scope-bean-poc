package io.github.victor_vn.request_scope_bean_poc.annotation;

import io.github.victor_vn.request_scope_bean_poc.domain.Product;
import io.github.victor_vn.request_scope_bean_poc.feign.PlaceholderFeignClient;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

@Aspect
@Component
public class LoggingAspect {

    private PlaceholderFeignClient feignClient;
    private Product product;

    public LoggingAspect(PlaceholderFeignClient feignClient, Product product) {
        this.feignClient = feignClient;
        this.product = product;
    }

    /*
    @Before("@within(io.github.victor_vn.request_scope_bean_poc.annotation.CustomAnnotation)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms JUST BEFORE");
        return proceed;
    }

    @After("@within(io.github.victor_vn.request_scope_bean_poc.annotation.CustomAnnotation)")
    public Object logExecutionTimeAfter(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms JUST AFTER");
        return proceed;
    }

    @Before("@annotation(preAction)") // Match methods annotated with @PreAction
    public void handlePreAction(CustomAnnotation preAction) {
        System.out.println("PreAction: " + preAction.value());
    }

    @After("@annotation(postAction)") // Match methods annotated with @PostAction
    public void handlePostAction(CustomAnnotation postAction) {
        System.out.println("PostAction: " + postAction.value());
    }*/

    @Before("@within(io.github.victor_vn.request_scope_bean_poc.annotation.CustomAnnotation)")
    public void handlePreAction(JoinPoint joinPoint) {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        CustomAnnotation annotation = targetClass.getAnnotation(CustomAnnotation.class);

        System.out.println("Product Value before PRE: " + product.getValue());

        // Access request data
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        if (annotation != null) {
            product.setValue(feignClient.getUserInfo(Integer.parseInt(request.getParameter("value"))).getTitle());
            System.out.println("PreAction: " + annotation.value());
        }
    }

    @After("@within(io.github.victor_vn.request_scope_bean_poc.annotation.CustomAnnotation)")
    public void handlePostAction(JoinPoint joinPoint) {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        CustomAnnotation annotation = targetClass.getAnnotation(CustomAnnotation.class);
        if (annotation != null) {
            System.out.println("PostAction - Product Value after POS: " + product.getValue());
        }
    }
}