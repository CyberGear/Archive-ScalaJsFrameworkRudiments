package lt.markav.core.style

import org.scalajs.dom.html.Element

import scalatags.JsDom
import scalatags.JsDom.all._

object MaterialIcons {

  val Add: JsDom.TypedTag[Element] = icon("add")

  val Done: JsDom.TypedTag[Element] = icon("done")

  private def icon(name: String) = i(`class` := "material-icons", name)
}
