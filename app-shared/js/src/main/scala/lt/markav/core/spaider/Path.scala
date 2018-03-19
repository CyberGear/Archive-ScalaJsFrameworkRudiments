package lt.markav.core.spaider

import org.scalajs.dom
import org.scalajs.dom.window

case class Path(parts: List[String]) {

  def / (other: Path) = Path(parts ++ other.parts)

  def startsWith(other: Path): Boolean = parts startsWith other.parts

  def after(other: Path): Path = Path(parts diff other.parts)

  def isEmpty: Boolean = parts.isEmpty
  def nonEmpty: Boolean = parts.nonEmpty
}

object Path {
  def apply(): Path = Path(List())

  def current: Path = Path(window.location.hash.split("/").tail.toList)

  implicit def toPath(part: String): Path = Path(List(part))

  implicit class StringPathUtils(part: String) {
    def / (otherPart: String): Path = Path(List(part, otherPart))
  }
}
