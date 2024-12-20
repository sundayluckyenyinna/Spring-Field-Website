package com.springfield.website.common;

import java.lang.annotation.*;

@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMetaArg {

    boolean authenticateCustomer() default true;
}
