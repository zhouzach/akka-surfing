package org.rabbit.scheduler

import akka.actor.{ActorSystem, Props}
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension
import com.typesafe.config.ConfigFactory

object AkkaScheduler extends App {

  val config = ConfigFactory.parseString(
    s"""
       |akka.http.server.remote-address-header = on
    """.stripMargin)
    .withFallback(ConfigFactory.load())

  implicit val system = ActorSystem("itoa", config)

  val scheduler = QuartzSchedulerExtension(system)

  val worker = system.actorOf(Props[WorkerActor])

  scheduler.createSchedule("job1",
    cronExpression = ConfigFactory.load().getString("akka.quartz.schedules.Every30Seconds.expression"))
  scheduler.schedule("job1", worker, Tick)

}
