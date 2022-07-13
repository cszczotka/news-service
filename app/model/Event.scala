package model

import play.api.libs.json.{JsValue, Json, Writes}
case class Event(id:String, author: String, title: String, description: String, eventImage: String, publishedAt: String, content: String)


object  Event {
  implicit val implicitWrites = new Writes[Event] {
    def writes(event: Event): JsValue = {
      Json.obj(
        "id" -> event.id,
        "title" -> event.title,
        "author" -> event.author,
        "publishedAt" -> event.publishedAt,
        "description" -> event.description,
        "eventImage" -> event.eventImage,
        "content" -> event.content
      )
    }
  }
}
