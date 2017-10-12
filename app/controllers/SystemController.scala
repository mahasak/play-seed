package controllers

import javax.inject.{Inject, Singleton}

import com.typesafe.scalalogging.LazyLogging
import messages.SystemStatus
import play.api.mvc.{Action, AnyContent, ControllerComponents}
import utilities.Conf

/**
  * Created by Peerapat A on Jul 13, 2017
  */
@Singleton
class SystemController @Inject()(cc: ControllerComponents) extends RESTController(cc)
  with LazyLogging {

  private lazy val system = SystemStatus(version = Conf("version"))

  logger.info(s"Started v : ${system.version}")

  def status: Action[AnyContent] = Action {
    ok(system)
  }

  def healthcheck: Action[AnyContent] = Action {
    NoContent
  }

}
