package org.rabbit.scheduler

import java.io.{File, PrintWriter}

import akka.actor.{Actor, ActorLogging}

class WorkerActor extends Actor with ActorLogging {
  var n: Int = 0

  override def receive: Receive = {
    case Tick =>
      val pw = new PrintWriter(new File("hello.txt" ))
      n += 1
      pw.write(n)
      pw.write("hello")
      pw.write("wordl")
      pw.close
  }

}

case class Tick()
