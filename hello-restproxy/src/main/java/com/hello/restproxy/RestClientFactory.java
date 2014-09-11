package com.hello.restproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RestClientFactory {
    

    public static RestClientFactory getInstance() {
        return new RestClientFactory();
    }

    @SuppressWarnings("unchecked")
    public <T> T getClient(Class<T> interfaceClass, String url) {

        InvocationHandler handler = new InvocationHandler() {

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                // First serialize request args to json
                String jsonRequest = javaToJson(args);

                // Run interceptors before() methods here

                String jsonResult = invokeRestOperation(jsonRequest); //Scan 'proxy' for REST endpoint url and HTTP method

                Object javaResult = jsonToJava(method.getReturnType(), jsonResult);

                // Run interceptors after () methods here

                return javaResult;
            }

        };

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass }, handler);
    }

    protected String invokeRestOperation(String jsonRequest) {
        // Use HTTP Client here fx
        System.out.println("Invoking rest service !"); //$NON-NLS-1$
        return null;
    }

    protected Object jsonToJava(Class<?> returnType, String jsonResult) {
        // Use GSON here fx
        return null;
    }

    protected String javaToJson(Object[] args) {
        // Use GSON here fx
        return null;
    }

}
