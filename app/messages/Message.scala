package messages

import utilities.Json


/**
  * Created by Peerapat A on Jul 17, 2017
  */
abstract class Message {

  def toText: String = Json.toJson(this)

}
