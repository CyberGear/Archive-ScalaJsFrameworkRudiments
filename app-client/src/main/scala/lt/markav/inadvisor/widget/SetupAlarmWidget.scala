package lt.markav.inadvisor.widget

import lt.markav.core.spaider.Path
import lt.markav.core.spaider.Widget

import scalatags.JsDom.all._

case class SetupAlarmWidget(override val path: Path = "setupalarm") extends Widget {
  override def contents: Tag = span("setupalarm")
}
