package lt.markav.inadvisor

import lt.markav.core.PageIds
import lt.markav.core.spaider.RootWidget
import lt.markav.inadvisor.widget.AlarmsFrameWidget
import lt.markav.inadvisor.widget.AlarmsWidget
import lt.markav.inadvisor.widget.EditAlarmFrameWidget
import lt.markav.inadvisor.widget.GeneralFrameWidget
import lt.markav.inadvisor.widget.LoginWidget
import lt.markav.inadvisor.widget.EditAlarmWidget
import org.scalajs.dom.Element
import org.scalajs.dom.document.getElementById
import rx._

object Client extends App {
  implicit val ctx: Ctx.Owner = Ctx.Owner.safe()

  private val mainContainer: Element = getElementById(PageIds.MainContainer)

  RootWidget(mainContainer, List(
    AlarmsFrameWidget(List(
      AlarmsWidget()
    )),
    GeneralFrameWidget(List(
      LoginWidget()
    )),
    EditAlarmFrameWidget(List(
      EditAlarmWidget()
    ))
  ))

}