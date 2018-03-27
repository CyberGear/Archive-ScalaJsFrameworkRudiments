package lt.markav.core.style

import org.scalajs.dom.html.Element

import scalatags.JsDom
import scalatags.JsDom.all._

object MaterialIcons {

  val add: JsDom.TypedTag[Element] = icon("add")

  private def icon(name: String) = i(`class` := "material-icons", name)
}
