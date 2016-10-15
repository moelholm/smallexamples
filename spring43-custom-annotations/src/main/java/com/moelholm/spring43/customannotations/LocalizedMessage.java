package com.moelholm.spring43.customannotations;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Autowired
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalizedMessage {

  String value() default "";

}
