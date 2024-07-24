package com.woowa.framework.web;

import jakarta.servlet.HttpMethodConstraintElement;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String path() default "";
    HttpMethod method() default HttpMethod.GET;
}
