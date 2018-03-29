package lt.markav.inadvisor.template

import org.scalajs.dom.html.Element

import scalatags.JsDom

trait TagsGroup {
  def generate: JsDom.TypedTag[Element]
}
