package controllers

import model.{Event, EventThin}
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc._

import javax.inject.{Inject, Singleton}


@Singleton
class NewsController @Inject()(val controllerComponents: ControllerComponents)
  extends BaseController {

  def searchNews(phrase: String): Action[AnyContent] = Action { implicit request =>
    val events:List[EventThin] = List();
    val r: Result = Ok("Ok")
    r
  }

  def getNLastNews(n: Int) : Action[AnyContent] = Action {
    val events:List[EventThin] = List(new EventThin(id="1", author = "Jonh", title = "test", publishedAt = ""),
      new EventThin(id="2", author = "Jonh", title = "test", publishedAt = ""));

    val json: JsValue = Json.toJson(events)
    val r: Result = Ok(json)
    r
  }

  def getNewById(id: String) : Action[AnyContent] = Action {
    val event = new Event(id = "1", title = "test", author = "test", content = "test",description= "", publishedAt = "", eventImage = "")
    val json: JsValue = Json.toJson(event)
    val r: Result = Ok(json)
    r
  }




}