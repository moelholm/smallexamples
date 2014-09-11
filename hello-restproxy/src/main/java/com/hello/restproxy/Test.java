package com.hello.restproxy;

public class Test {

    public static void main(String[] args) {

        HelloService service = RestClientFactory.getInstance().getClient(HelloService.class, "/url/to/my/endpoint"); //$NON-NLS-1$

        service.sayHello();
    }
}
