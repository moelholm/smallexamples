package com.moelholm

import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

@ApplicationPath("/api")
class HelloJaxRsApplication : Application() {

    override fun getClasses() = mutableSetOf(HelloResource::class.java)

}