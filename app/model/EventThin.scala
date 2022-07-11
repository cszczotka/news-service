package model

import play.api.libs.json.{JsValue, Json, Writes}

case class EventThin(id:String, author: String, title: String, publishedAt: String)

object  EventThin {
  implicit val implicitWrites = new Writes[EventThin] {
    def writes(event: EventThin): JsValue = {
      Json.obj(
        "id" -> event.id,
        "title" -> event.title,
        "author" -> event.author,
        "publishedAt" -> event.publishedAt
      )
    }
  }
}

