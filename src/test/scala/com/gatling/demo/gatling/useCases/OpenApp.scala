package com.gatling.demo.gatling.useCases

import com.gatling.demo.gatling.configuration.Configuration
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import jodd.util.URLDecoder
import scala.concurrent.duration._


object OpenApp  {

    val header_csrf = Map(
        "Accept" -> """application/json""",
        "X-XSRF-TOKEN" -> "${csrf_token}"
    )

	val useCase1 = 
        exec(
            http("Open Page")
            .get("/")
            .check(headerRegex("Set-Cookie", "XSRF-TOKEN=(.*); [P,p]ath=/").saveAs("csrf_token"))
            .headers(Map("Accept" -> """application/json"""))
             )
     
     	val useCase2 = 
        exec(
            http("Log In")
			.post("/api/login")
            .basicAuth("admin","admin")
            .headers(header_csrf)
            
            .get("/app/components/app/app.html")
            .headers(header_csrf)
            .check(headerRegex("Set-Cookie", "XSRF-TOKEN=(.*); [P,p]ath=/").saveAs("csrf_token"))
            )
            
	val useCase3 = 
  exec(http("Get Projects")
		.get("/api/projects?limit=5&page=1")
        .headers(header_csrf)
        )       
        
     	val useCase = 
  exec(http("Log Out")
		.post("/api/logout")
        .headers(header_csrf)
        .check(status.is(401)))

}
