package controllers

import messages.Message
import play.api.mvc.{AbstractController, ControllerComponents, Result}

/**
  * @author Peerapat A, Sep 13, 2017
  * @param cc : ControllerComponents
  */
abstract class RESTController(cc: ControllerComponents) extends AbstractController(cc) {

  protected def ok(m: Message): Result = Ok(m.toText)
    .withHeaders(HttpHeader.HEADERS: _*) as "application/json"

}
