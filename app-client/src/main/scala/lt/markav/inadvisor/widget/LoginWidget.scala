package lt.markav.inadvisor.widget

import lt.markav.core.spaider.Path
import lt.markav.core.spaider.Widget

import scalatags.JsDom.all._

case class LoginWidget(override val path: Path = "login") extends Widget {
  override def contents: Tag = span("login")
}
