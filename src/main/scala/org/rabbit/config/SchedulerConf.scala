package org.rabbit.config

import java.util.concurrent.ConcurrentHashMap

class SchedulerConf extends ClientConf[SchedulerConf] {

  override val configs: ConcurrentHashMap[String, Any] = getConfigs("akka.quartz", "scheduler.conf")

  def expression() = get[String]("schedules.Every30Seconds.expression").getOrElse("*/2 * * ? * *")

}

object SchedulerConf {
  def apply: SchedulerConf = new SchedulerConf()
}