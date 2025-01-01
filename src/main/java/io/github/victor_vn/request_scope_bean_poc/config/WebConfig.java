package io.github.victor_vn.request_scope_bean_poc.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ApplicationContext context;

    public WebConfig(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        context.getBeansOfType(HandlerInterceptor.class)
                .values()
                .forEach(registry::addInterceptor);
    }
}

