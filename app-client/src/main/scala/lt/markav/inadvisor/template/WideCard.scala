package lt.markav.inadvisor.template

import lt.markav.core.style.IaClass
import lt.markav.core.style.Mdl
import org.scalajs.dom.html.Element

import scalatags.JsDom
import scalatags.JsDom.all._

case class WideCard(contents: JsDom.TypedTag[Element], label: String = "") extends TagsGroup {

  private val title: Option[JsDom.TypedTag[Element]] =
    if (label.isEmpty) None
    else Some(
      div(Mdl.title, Mdl.cardTitle,
        label))

  override def generate: JsDom.TypedTag[Element] = div(
    IaClass.cardWide, Mdl.mdlCard, Mdl.mdlShadow2dp, Mdl.mdlCell,
    title,
    contents
  )

}
