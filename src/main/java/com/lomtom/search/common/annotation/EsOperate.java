package com.lomtom.search.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/11
 * @Time: 21:00
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EsOperate {

    String value() default "";

    int type() default 0;
}
