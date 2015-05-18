package com.moelholm.importantstuff;

import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;

import com.moelholm.profiling.Service;

@Stateless
@Service
public class GreetingRepositoryBean {

    public String getGreeting(String caller) {

        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return String.format("Mjello, %s", caller);
    }

}
