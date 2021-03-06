package controllers

import model.{Event, EventThin}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import repository.EventRepository

import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext


@Singleton
class EventController @Inject()(val controllerComponents: ControllerComponents,
                                val eventRepository: EventRepository)(implicit ec: ExecutionContext) extends BaseController {


  private val logger = play.api.Logger(this.getClass)
  private val dateFormat = new SimpleDateFormat("d-M-y")

  def searchEvents(phrase: String): Action[AnyContent] = Action { implicit request =>
    val events:List[EventThin] = List()
    val r: Result = Ok("Ok")
    r
  }

  def getNLastEvents(n: Int) : Action[AnyContent] = Action {
    val events:Seq[EventThin] =eventRepository.findLastN(n)
    val json: JsValue = Json.toJson(events)
    val r: Result = Ok(json)
    r
  }

  def getEventById(id: String) : Action[AnyContent] = Action {
    val event = eventRepository.findById(id)
    val json: JsValue = Json.toJson(event)
    val r: Result = Ok(json)
    r
  }

  def createEvent(title: String, author: String, content: String, description: String, eventImage: String)  : Action[AnyContent] = Action {
    val publishedAt = dateFormat.format(Calendar.getInstance().getTime())
    val event = new Event(id = java.util.UUID.randomUUID.toString, title = title, author = author, content = content, description= description, publishedAt = publishedAt, eventImage =eventImage)
    eventRepository.insertEvent(event)
    logger.info("event has been created")
    Ok("Ok")
  }

  def updateEvent(id: String, title: Option[String], author: Option[String], content: Option[String], description: Option[String], eventImage: Option[String]): Action[AnyContent] = Action {
    Ok("Ok")
  }

  def deleteEvent(id: String) : Action[AnyContent] = Action {
    Ok("Ok")
  }

}