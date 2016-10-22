package com.moelholm;

import org.springframework.test.annotation.IfProfileValue;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@IfProfileValue(name="testprofile", value="unittest")
public @interface UnitTest {
}
