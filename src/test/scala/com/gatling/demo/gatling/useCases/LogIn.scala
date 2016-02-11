package com.gatling.demo.gatling.useCases

import com.gatling.demo.gatling.configuration.Configuration
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import jodd.util.URLDecoder
import scala.concurrent.duration._


object LogIn  {



	val useCase = 
        exec(
            http("Logging In")
			.post("/api/login")
            .basicAuth("admin","admin")
            .headers(header_csrf)
            )
            .get("/app/components/app/app.html")
            .check(headerRegex("Set-Cookie", "CSRF-TOKEN=(.*); [P,p]ath=/").saveAs("csrf_token"))


}
