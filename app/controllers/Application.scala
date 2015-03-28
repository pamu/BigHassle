package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json._
import play.api.libs.functional.syntax._


object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("BigHassle"))
  }

  case class FoodInfo(description: String, special: Option[String] = None)
  case class SlotInfo(slotNo: Int, name: String, startTime: String, span: Int, 
      foodInfo: FoodInfo)
  case class DayInfo(slots: List[SlotInfo])
  case class MenuInfo(days: List[DayInfo])
  
  implicit val foodInfoReads: Reads[FoodInfo] = (
      (JsPath \ "description").read[String] and
      (JsPath \ "special").read[Option[String]] 
  )(FoodInfo.apply _)
  
  implicit val slotInfoReads: Reads[SlotInfo] = (
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
  
  def slot(day: Int, slot: Int) = Action { implicit request =>
    import models._
    if (day >= 0 && day < 7) {
      if (slot >= 0 && slot < 4) {
        val info = DAO.menuInfo.days(day).slots(slot)
        Ok(Json.toJson(info))
      } else {
        BadRequest(Json.obj("msg" -> "slot should be between 0 and 3"))
      }
    } else {
      BadRequest(Json.obj("msg" -> "day should be between 0 and 6"))
    }
    
  }

  def day(day: Int) = Action { implicit request =>
    import models._
    
    if (day >= 0 && day < 7) {
      val info = DAO.menuInfo.days(day)
      Ok(Json.toJson(info))
    } else {
      BadRequest(Json.obj("msg" -> "day should be between 0 and 3"))
    }
    
  }
  
  def menu() = Action { implicit request =>
    import models._
    Ok(Json.toJson(DAO.menuInfo))
  }

}
