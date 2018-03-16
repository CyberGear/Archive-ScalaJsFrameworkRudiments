package lt.markav.core.api

trait AlarmsApi {

  def listAlarms(userId: String): List[String]

}
