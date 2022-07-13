package controllers

import model.{Event, EventThin}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import repository.NewsRepository

import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext


@Singleton
class NewsController @Inject()(val controllerComponents: ControllerComponents,
                               val newsRepository: NewsRepository)(implicit ec: ExecutionContext) extends BaseController {


  private val logger = play.api.Logger(this.getClass)
  private val dateFormat = new SimpleDateFormat("d-M-y")

  def searchNews(phrase: String): Action[AnyContent] = Action { implicit request =>
    val events:List[EventThin] = List()
    val r: Result = Ok("Ok")
    r
  }

  def getNLastNews(n: Int) : Action[AnyContent] = Action {
    val events:Seq[EventThin] =newsRepository.findLastN(n)
    val json: JsValue = Json.toJson(events)
    val r: Result = Ok(json)
    r
  }

  def getNewById(id: String) : Action[AnyContent] = Action {
    val event = newsRepository.findById(id)
    val json: JsValue = Json.toJson(event)
    val r: Result = Ok(json)
    r
  }

  def createNews(title: String, author: String, content: String, description: String, eventImage: String)  : Action[AnyContent] = Action {
    val publishedAt = dateFormat.format(Calendar.getInstance().getTime())
    val event = new Event(id = java.util.UUID.randomUUID.toString, title = title, author = author, content = content, description= description, publishedAt = publishedAt, eventImage =eventImage)
    newsRepository.insertNewEvent(event)
    logger.info("event has been created")
    Ok("Ok")
  }
}