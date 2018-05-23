package org.rabbit.scheduler

import java.io.{File, PrintWriter}

import akka.actor.{Actor, ActorLogging, ReceiveTimeout}
import org.joda.time.DateTime
import org.slf4j.LoggerFactory

class WorkerActor extends Actor with ActorLogging {

  val logger = LoggerFactory.getLogger(this.getClass)
  override def receive: Receive = {
    case tick: Tick =>
      val pw = new PrintWriter(new File("hello.txt" ))
      pw.write(s"${tick.increment()}")
      pw.write(s"hello ${tick.date()}\n")
      pw.close
      logger.info(s"scheduled at ${tick.date()}!")

    case ReceiveTimeout =>
      logger.warn("can't scheduled because timed out!")
  }

}

case class Tick() {
  var n: Int = 0

  def date() = {
    DateTime.now()
  }

  def increment() = {
    n += 1
    n
  }

}
