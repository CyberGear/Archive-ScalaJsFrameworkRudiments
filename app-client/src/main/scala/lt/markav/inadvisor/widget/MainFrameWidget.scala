package lt.markav.inadvisor.widget

import lt.markav.core.spaider.Widget
import lt.markav.core.style.MaterialIcons
import lt.markav.core.style.Mdl
import lt.markav.inadvisor.template.Template
import org.scalajs.dom.html.Element

import scalatags.JsDom
import scalatags.JsDom.all._

abstract class MainFrameWidget(val header: Element) extends Widget {
  override def contents: Tag =
    div(Mdl.layout, Mdl.jsLayout, Mdl.layoutFixedHeader,
      header,
      Template.Main(page))
}

case class GeneralFrameWidget(override val widgets: List[Widget])
  extends MainFrameWidget(Template.Header)

case class AlarmsFrameWidget(override val widgets: List[Widget])
  extends MainFrameWidget(Template.HeaderFab(MaterialIcons.Add))

case class EditAlarmFrameWidget(override val widgets: List[Widget])
  extends MainFrameWidget(Template.HeaderFab(MaterialIcons.Done))
