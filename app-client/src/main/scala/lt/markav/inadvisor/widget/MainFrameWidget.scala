package lt.markav.inadvisor.widget

import lt.markav.core.spaider.Widget
import lt.markav.core.style.Mdl
import lt.markav.inadvisor.template.Template

import scalatags.JsDom.all._

case class MainFrameWidget(override val widgets: List[Widget]) extends Widget {
  override def contents: Tag = div(Mdl.layout, Mdl.jsLayout, Mdl.layoutFixedHeader,
    Template.Header,
    page
  )
}
