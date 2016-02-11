package com.gatling.demo.gatling.useCases

import com.gatling.demo.gatling.configuration.Configuration
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import jodd.util.URLDecoder
import scala.concurrent.duration._


object GetProjects  {



	val useCase = 
  exec(http("Get Projects")
		.get("/api/projects?limit=5&page=1")
        .headers(header_csrf)
        )


}
