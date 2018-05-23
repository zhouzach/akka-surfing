package org.rabbit.config

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

object Init {

  val config = ConfigFactory.parseString(
    s"""
       |akka.http.server.remote-address-header = on
    """.stripMargin)
    .withFallback(ConfigFactory.load())

  implicit val system = ActorSystem("Akka-surfing", config)

}
