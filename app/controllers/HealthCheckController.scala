package controllers

import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents, Result}

import javax.inject.{Inject, Singleton}

@Singleton
class HealthCheckController @Inject()(val controllerComponents: ControllerComponents)
  extends BaseController {

  def healthCheck(): Action[AnyContent] = Action { implicit request =>
    val r: Result = Ok("Up")
    r
  }
}
