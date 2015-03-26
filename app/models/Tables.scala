package models

import scala.slick.driver.MySQLDriver.simple._  

object Tables {
  import controllers.Application._
  
  case class Food(description: String, special: Option[String] = None, id: Option[Long] = None)
  class Foods(tag: Tag) extends Table[Food](tag, "foods") {
    def description = column[String]("description", O.NotNull)
    def special = column[String]("special", O.Nullable)
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def * = (description, special.?, id.?) <> (Food.tupled, Food.unapply)
  }
  
  case class Slot(slotNo: Int, name: String, startTime: String, span: Int, foodId: Long, id: Option[Long]  = None)
  class Slots(tag: Tag) extends Table[Slot](tag, "slots") {
    def slotNo = column[Int]("slotNo", O.NotNull)
    def name = column[String]("name", O.NotNull)
    def startTime = column[String]("startTime", O.NotNull)
    def span = column[Int]("span", O.NotNull)
    def foodId = column[Long]("foodId", O.NotNull)
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def * = (slotNo, name, startTime, span, foodId, id.?) <> (Slot.tupled, Slot.unapply)
    def foodIdFk = foreignKey("foodIdFk", foodId, TableQuery[Foods])(_.id, ForeignKeyAction.Cascade, ForeignKeyAction.Cascade)
  }
  
  case class Day(breakfast: Long, lunch: Long, snacks: Long, dinner: Long, id: Option[Long] = None)
  class Days(tag: Tag) extends Table[Day](tag, "days") {
    def breakfast = column[Long]("breakfast", O.NotNull)
    def lunch = column[Long]("lunch", O.NotNull)
    def snacks = column[Long]("snacks", O.NotNull)
    def dinner = column[Long]("dinner", O.NotNull)
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def * = (breakfast, lunch, snacks, dinner, id.?) <> (Day.tupled, Day.unapply)
    def breakfastFk = foreignKey("breakfastFk", breakfast, TableQuery[Slots])(_.id, ForeignKeyAction.Cascade, ForeignKeyAction.Cascade)
    def lunchFk = foreignKey("lunchFk", lunch, TableQuery[Slots])(_.id, ForeignKeyAction.Cascade, ForeignKeyAction.Cascade)
    def snacksFk = foreignKey("scanksFk", snacks, TableQuery[Slots])(_.id, ForeignKeyAction.Cascade, ForeignKeyAction.Cascade)
    def dinnerFk = foreignKey("dinnerFk", dinner, TableQuery[Slots])(_.id, ForeignKeyAction.Cascade, ForeignKeyAction.Cascade)
  }
  
  case class Menu(sun: Long, mon: Long, tue: Long, wed: Long, thu: Long, fri: Long, sat: Long, 
      id: Option[Long] = None)
  class Menus(tag: Tag) extends Table[Menu](tag, "menus") {
    def sun = column[Long]("sun", O.NotNull)
    def mon = column[Long]("mon", O.NotNull)
    def tue = column[Long]("tue", O.NotNull)
    def wed = column[Long]("wed", O.NotNull)
    def thu = column[Long]("thu", O.NotNull)
    def fri = column[Long]("fri", O.NotNull)
    def sat = column[Long]("sat", O.NotNull)
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def * = (sun , mon, tue, wed, thu, fri, sat, id.?) <> (Menu.tupled, Menu.unapply)
    def sunFk = foreignKey("sunFk", sun, TableQuery[Days])(_.id, ForeignKeyAction.Cascade, ForeignKeyAction.Cascade)
    def monFk = foreignKey("monFk", mon, TableQuery[Days])(_.id, ForeignKeyAction.Cascade, ForeignKeyAction.Cascade)
    def tueFk = foreignKey("tueFk", tue, TableQuery[Days])(_.id, ForeignKeyAction.Cascade, ForeignKeyAction.Cascade)
    def wedFk = foreignKey("wedFk", wed, TableQuery[Days])(_.id, ForeignKeyAction.Cascade, ForeignKeyAction.Cascade)
    def thuFk = foreignKey("thuFk", thu, TableQuery[Days])(_.id, ForeignKeyAction.Cascade, ForeignKeyAction.Cascade)
    def friFk = foreignKey("friFk", fri, TableQuery[Days])(_.id, ForeignKeyAction.Cascade, ForeignKeyAction.Cascade)
    def satFk = foreignKey("satFk", sat, TableQuery[Days])(_.id, ForeignKeyAction.Cascade, ForeignKeyAction.Cascade)
  }
  
}