# Spring Proxy Factory example

**WARNING: Consider using Spring AOP before resorting to proxying this way! It will most certainly suit your needs.** 

Illustrates how to use a Spring BeanPostProcessor to proxy a specific bean.
 
The proxy itself is generated using Spring's ProxyFactory - and it is spiced up with a simple MethodBeforeAdvice.

**WARNING: Consider using Spring AOP before resorting to proxying this way! It will most certainly suit your needs.** 