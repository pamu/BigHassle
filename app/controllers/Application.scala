package controllers

import play.api.mvc.{Action, Controller}

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("BigHassle"))
  }

  case class FoodInfo(description: String, special: Option[String] = None)
  case class SlotInfo(dayNo: Int, slotNo: Int, name: String, startTime: String, span: Int, foodInfo: FoodInfo)
  case class DayInfo(slots: List[SlotInfo])
  case class MenuInfo(days: List[DayInfo])
  
  def slot(day: Int, slot: Int) = Action(parse.json) { implicit request =>
    Ok("")
  }

  def day(day: Int) = Action(parse.json) { implicit request =>
    Ok("")
  }
  
  def menu() = Action(parse.json) { implicit request =>
    Ok("")
  }

}
