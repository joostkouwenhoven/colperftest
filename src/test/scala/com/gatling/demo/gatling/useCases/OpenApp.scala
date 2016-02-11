package com.gatling.demo.gatling.useCases

import com.gatling.demo.gatling.configuration.Configuration
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import jodd.util.URLDecoder
import scala.concurrent.duration._


object OpenApp  {

	val useCase1 = 
        exec(
            http("Open Page")
            .get("/")
            .check(headerRegex("Set-Cookie", "XSRF-TOKEN=(.?*);").saveAs("csrf_token"))
            .check(headerRegex("Set-Cookie", "SESSION=(.?*);").saveAs("session"))
            .headers(Map("Accept" -> """application/json"""))
             )
     
     	val useCase2 = 
        exec(
            http("Log In")
			.post("/api/login")
            .basicAuth("admin","admin")
            .headers(Map("XSRF-TOKEN" -> "${csrf_token}", "SESSION" -> "${session}"))
            .check(headerRegex("Set-Cookie", "SESSION=(.?*);").saveAs("session"))
            )
        exec(
            http("Confirm Login")           
            .get("/app/components/app/app.html")
            .headers(Map("XSRF-TOKEN" -> "${csrf_token}", "SESSION" -> "${session}"))
            .check(headerRegex("Set-Cookie", "XSRF-TOKEN=(.?*);").saveAs("csrf_token"))
             )
	val useCase3 = 
  exec(http("Get Projects")
		.get("/api/projects?limit=5&page=1")
        .headers(Map("XSRF-TOKEN" -> "${csrf_token}", "SESSION" -> "${session}"))
        )       
        
     	val useCase4 = 
  exec(http("Log Out")
		.post("/api/logout")
        .headers(Map("XSRF-TOKEN" -> "${csrf_token}", "SESSION" -> "${session}"))
        .check(status.is(401)))

}
