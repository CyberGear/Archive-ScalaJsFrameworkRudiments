package lt.markav.core

import org.scalajs.dom

case class WebUrl(path: String*) {
  private val `#` = "#"

  override def toString: String =
    if (path.isEmpty) ""
    else `#` +: path mkString "/"

}

object WebUrl {
  val empty: WebUrl = WebUrl(List.empty: _*)
  def apply(): WebUrl = WebUrl(dom.window.location.hash.split("/").tail: _*)
}