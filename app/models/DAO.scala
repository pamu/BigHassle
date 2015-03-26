package models

object DAO {
  import controllers.Application._
  
  val dinner = FoodInfo("rice, roti, dal, brinjal, caroots, kheera and radish splices", Some("Julaab Jamun"))
  val snack = FoodInfo("bread pakida", Some("Coffee"))
  val lunch = FoodInfo("roti, biryani", Some("Biryani lunch"))
  val breakfast = FoodInfo("parata", Some("Special Punjabi Parata"))
  
  val slotInfo = SlotInfo(List[FoodInfo](breakfast, lunch, snack, dinner))
}