package com.moelholm

import javax.ejb.Stateless

@Stateless
class HelloBean {

    fun sayHello(caller: String) = "Hello, $caller"

}