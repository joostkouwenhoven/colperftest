package com.gatling.demo.gatling.useCases

import com.gatling.demo.gatling.configuration.Configuration
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import jodd.util.URLDecoder
import scala.concurrent.duration._


object LogOut  {



	val useCase = 
  exec(http("Log Out")
		.post("/api/logout")
        .headers(header_csrf)
        .check(status.is(401)))


}