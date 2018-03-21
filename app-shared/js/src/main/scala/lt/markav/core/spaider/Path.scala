package lt.markav.core.spaider

import lt.markav.core.util.Logging
import org.scalajs.dom.window

case class Path(parts: List[String]) extends Logging {

  def /(other: Path) = Path(parts ++ other.parts)

  def startsWith(other: Path): Boolean = {
    log(s"$toString startsWith $other == ${parts startsWith other.parts}")
    parts startsWith other.parts
  }

  def after(other: Path): Path = Path(parts diff other.parts)

  def isEmpty: Boolean = parts.isEmpty

  def nonEmpty: Boolean = parts.nonEmpty

  def depth: Int = parts.length

  override def toString: String = parts.mkString("#/", "/", "")
}

object Path extends Logging {
  def apply(): Path = Path(List())

  def apply(parts: String*): Path = Path(parts.toList)

  def current: Path = Path(window.location.hash.split("/").tail.toList).lg("current")

  implicit def toPath(part: String): Path = Path(List(part))

}
