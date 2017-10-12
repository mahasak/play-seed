package utilities

import com.typesafe.config.ConfigFactory

import scala.util.Try

/**
  * Created by Peerapat A on Mar 18, 2017
  */
object Conf {

  private val conf = ConfigFactory.load()

  def apply(key: String): String = conf.getString(key)
  def apply(key: String, default: String): String = Try(conf.getString(key)).getOrElse(default)

  def bool(key: String): Boolean = conf.getBoolean(key)
  def bool(key: String, default: Boolean): Boolean = Try(conf.getBoolean(key)).getOrElse(default)

}
