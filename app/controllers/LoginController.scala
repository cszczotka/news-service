package controllers

import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import javax.inject.{Inject, Singleton}

@Singleton
class LoginController @Inject()(val controllerComponents: ControllerComponents)
  extends BaseController {

  def login(user: String, password: String): Action[AnyContent] = Action {
    NoContent
  }
}
