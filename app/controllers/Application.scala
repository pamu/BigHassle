package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json._
import play.api.libs.functional.syntax._


object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("BigHassle"))
  }

  case class FoodInfo(description: String, special: Option[String] = None)
  case class SlotInfo(dayNo: Int, slotNo: Int, name: String, startTime: String, span: Int, 
      foodInfo: FoodInfo)
  case class DayInfo(slots: List[SlotInfo])
  case class MenuInfo(days: List[DayInfo])
  
  implicit val foodInfoReads: Reads[FoodInfo] = (
      (JsPath \ "description").read[String] and
      (JsPath \ "special").read[Option[String]] 
  )(FoodInfo.apply _)
  
  implicit val slotInfoReads: Reads[SlotInfo] = (
      (JsPath \ "dayNo").read[Int] and
      (JsPath \ "slotNo").read[Int] and
      (JsPath \ "name").read[String] and
      (JsPath \ "startTime").read[String] and
      (JsPath \ "span").read[Int] and
      (JsPath \ "foodInfo").read[FoodInfo]
  )(SlotInfo.apply _)
  
  implicit val dayInfoReads: Reads[DayInfo] = (
      (JsPath \ "slots").read[List[SlotInfo]].map(DayInfo(_))
  )
  
  implicit val menuInfoReads: Reads[MenuInfo] = (
      (JsPath \ "days").read[List[DayInfo]].map(MenuInfo(_))
  )
  
  implicit val foodInfoWrites: Writes[FoodInfo] = (
      (JsPath \ "description").write[String] and
      (JsPath \ "special").write[Option[String]]
  )(unlift(FoodInfo.unapply))
  
  implicit val slotInfoWrites: Writes[SlotInfo] = (
      (JsPath \ "dayNo").write[Int] and
      (JsPath \ "slotNo").write[Int] and
      (JsPath \ "name").write[String] and
      (JsPath \ "startTime").write[String] and
      (JsPath \ "span").write[Int] and
      (JsPath \ "foodInfo").write[FoodInfo]
  )(unlift(SlotInfo.unapply))
  
  implicit val dayInfoWrites: Writes[DayInfo] = (
      (JsPath \ "slots").write[List[SlotInfo]].contramap[DayInfo](_.slots)
  )
  
  implicit val menuInfoWrites: Writes[MenuInfo] = (
     (JsPath \ "days").write[List[DayInfo]].contramap[MenuInfo](_.days)
  )
  
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
