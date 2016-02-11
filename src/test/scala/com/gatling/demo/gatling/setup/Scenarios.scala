package com.gatling.demo.gatling.setup

import com.gatling.demo.gatling.useCases._
import com.gatling.demo.gatling.feeders._
import io.gatling.core.Predef._
import scala.concurrent.duration._

/**
 * This object collects the Scenarios in the project for use in the Simulation. There are two
 * main properties in this object: acceptanceTestScenario and debugScenario. These two are
 * used in the Simulation class to setup the actual tests to run. If you wish to add
 * scenarios to either run, add them here. 
 */
object Scenarios {

  /**
   * These are the scenarios run in 'normal' mode.
   */
 
   
  val acceptanceTestScenario = scenario("acceptanceTestScenario")
    .exec(
      	val useCase1 = 
        exec(
            http("Open Page")
            .get("/")
            .check(headerRegex("Set-Cookie", "XSRF-TOKEN=(.?*);").saveAs("csrf_token"))
            .headers(Map("Accept" -> """application/json"""))
             )
     
     	val useCase2 = 
        exec(
            http("Log In")
			.post("/api/login")
            .basicAuth("admin","admin")
            .headers(Map("XSRF-TOKEN" -> "${csrf_token}"))
            )
        exec(
            http("Confirm Login")           
            .get("/app/components/app/app.html")
            .headers(Map("XSRF-TOKEN" -> "${csrf_token}"))
            .check(headerRegex("Set-Cookie", "XSRF-TOKEN=(.?*);").saveAs("csrf_token"))
             )
	val useCase3 = 
  exec(http("Get Projects")
		.get("/api/projects?limit=5&page=1")
        .headers(Map("XSRF-TOKEN" -> "${csrf_token}"))
        )       
        
     	val useCase4 = 
  exec(http("Log Out")
		.post("/api/logout")
        .headers(Map("XSRF-TOKEN" -> "${csrf_token}"))
        .check(status.is(401)))
        )

  /**
   * These are the scenarios run in 'debug' mode.
   */
  val debugScenario = scenario("debug")
    .exec(OpenApp.useCase1)

}
