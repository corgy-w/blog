package cn.corgy.blog.config.aop;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Uvlog {
    String type() default "";
}