package org.rabbit.config

import java.io.File
import java.util.Map.Entry
import java.util.concurrent.ConcurrentHashMap

import com.typesafe.config.{Config, ConfigFactory, ConfigValue}

trait ClientConf[C <: ClientConf[C]] {

  val configs: ConcurrentHashMap[String, Any]

  def getConfigs(key: String, fileName: String = "app.conf"): ConcurrentHashMap[String, Any] = {
    val hashMap = new ConcurrentHashMap[String, Any]()
    val config = loadFromResource(key, fileName)
    for (entry <- config.entrySet().toArray) {
      val obj = entry.asInstanceOf[Entry[String, ConfigValue]]
      hashMap.put(obj.getKey, obj.getValue.unwrapped())
    }
    hashMap
  }

  def get[V](key: String): Option[V] = {
    configs.get(key) match {
      case x: V => Some(x)
      case _ => None
    }
  }

  private def loadFromResource(key: String, fileName: String): Config = {
    val file = new File(".", fileName)
    if (file.exists()) {
      loadConfig(file).getConfig(key)
    } else {
      ConfigFactory.load(fileName).getConfig(key)
    }
  }

  private def loadConfig(file: File): Config = {
    ConfigFactory.parseFile(file)
  }

}
