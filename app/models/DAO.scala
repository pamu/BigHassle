package models

object DAO {
  import controllers.Application._
  
  val dinner = FoodInfo("rice, roti, dal, brinjal, caroots, kheera and radish splices", Some("Julaab Jamun"))
  val snack = FoodInfo("bread pakida", Some("Coffee"))
  val lunch = FoodInfo("roti, biryani", Some("Biryani lunch"))
  val breakfast = FoodInfo("parata", Some("Special Punjabi Parata"))
  
  val slot0 = SlotInfo(0, "breakfast", "7:30", 2, breakfast)
  val slot1 = SlotInfo(1, "lunch", "12:00", 2, lunch)
  val slot2 = SlotInfo(2, "snack", "5:00", 1, snack)
  val slot3 = SlotInfo(3, "dinner", "7:30", 2, dinner)
  
  val dayInfo = DayInfo(List[SlotInfo](slot0, slot1, slot2, slot3))
  
  val menuInfo = MenuInfo(List.fill[DayInfo](7)(dayInfo))
}