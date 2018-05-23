package org.rabbit.scheduler

import java.io.File
import java.util.TimeZone

import akka.actor.Props
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.joran.JoranConfigurator
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension
import org.rabbit.config.SchedulerConf
import org.slf4j.LoggerFactory

object AkkaScheduler {

  def main(args: Array[String]): Unit = {

    // have to config logback, otherwise never log
    initLogConfig()

    import org.rabbit.config.Init.system
    val worker = system.actorOf(Props[WorkerActor])
    val scheduler = QuartzSchedulerExtension(system)

    scheduler.createSchedule(
      "job1",
      cronExpression = SchedulerConf.apply.expression(),
//      calendar = Some("akka.quartz.calendars.HourOfTheWolf"),
      timezone = TimeZone.getDefault
//        timezone = TimeZone.getTimeZone("GMT+8:00")
//        timezone = TimeZone.getTimeZone("Asia/Shanghai")
    )
    scheduler.schedule("job1", worker, Tick())

  }

  private def initLogConfig(): Unit = {
    val logConfig = new File("./logback.xml")

    if (logConfig.exists() && logConfig.canRead) {
      val logContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]

      val config = new JoranConfigurator()
      config.setContext(logContext)

      logContext.reset()

      config.doConfigure(logConfig)
    }
  }

}
