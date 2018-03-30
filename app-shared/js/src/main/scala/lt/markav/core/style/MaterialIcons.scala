package lt.markav.core.style

import org.scalajs.dom.html.Element

import scalatags.JsDom
import scalatags.JsDom.all._

case class MaterialIcon(name: String, additionalClasses: List[TagClass] = List.empty) {
  def apply(additionalClasses: TagClass*): MaterialIcon = copy(additionalClasses = additionalClasses.toList)
  def render:JsDom.TypedTag[Element] =
    i(`class` := ("material-icons" +: additionalClasses.map(_.`class`)).mkString(" "), name)
}

object MaterialIcon {

  implicit def boxToIcon(name: String): MaterialIcon = MaterialIcon(name)

  implicit def unboxToJsDomTypedTag(icon: MaterialIcon): JsDom.TypedTag[Element] = icon.render

}

object MaterialIcons {

  val Add: MaterialIcon = "add"
  val Done: MaterialIcon = "done"
  val TrendingUp: MaterialIcon = "trending_up"
  val TrendingDown: MaterialIcon = "trending_down"

}
