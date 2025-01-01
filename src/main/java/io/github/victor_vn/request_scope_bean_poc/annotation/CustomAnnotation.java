package io.github.victor_vn.request_scope_bean_poc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Specify the annotation's target (e.g., method, field, class)
@Target({ElementType.METHOD, ElementType.TYPE})

// Specify the retention policy (e.g., runtime, source, class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnnotation {
    String value();  // A required parameter
    int number() default 0; // An optional parameter with a default value
}

