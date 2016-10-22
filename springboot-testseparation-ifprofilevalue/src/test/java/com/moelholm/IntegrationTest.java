package com.moelholm;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@IfProfileValue(name="testprofile", value="integrationtest")
@SpringBootTest
public @interface IntegrationTest {
}
