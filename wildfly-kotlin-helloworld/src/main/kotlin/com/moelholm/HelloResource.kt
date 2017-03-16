package com.moelholm

import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam

@Path("/hello")
class HelloResource @Inject constructor(val helloBean: HelloBean) {

    @GET
    @Path("/{caller}")
    fun get(@PathParam("caller") caller: String) = helloBean.sayHello(caller)

}