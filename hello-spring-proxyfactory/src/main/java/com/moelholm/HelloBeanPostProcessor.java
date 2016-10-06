package com.moelholm;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class HelloBeanPostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) {
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) {

    if (GreeterService.class != bean.getClass()) {
      return bean;
    }

    ProxyFactory factory = new ProxyFactory(bean);
    factory.addAdvice(new HelloBeforeAdvice());

    return factory.getProxy();
  }
}
