package lt.markav.inadvisor

import lt.markav.core.api.AlarmsApi

class AlarmsApiImpl extends AlarmsApi {
  override def listAlarms(userId: String): List[String] = List("viens", "du", "trys")
}