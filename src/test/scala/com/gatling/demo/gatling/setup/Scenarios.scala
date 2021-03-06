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
    .exec(OpenApp.useCase1)
    .pause(2)
    .exec(OpenApp.useCase2)
    .pause(2)
    .exec(OpenApp.useCase3)
    .pause(2)
    .exec(OpenApp.useCase4)
  /**
   * These are the scenarios run in 'debug' mode.
   */
  val debugScenario = scenario("debug")
    .exec(OpenApp.useCase1)

}
