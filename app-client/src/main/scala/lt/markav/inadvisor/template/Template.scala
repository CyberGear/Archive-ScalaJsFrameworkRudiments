package lt.markav.inadvisor.template

import lt.markav.core.PageIds
import lt.markav.core.TagIdJsDomImplicits._
import lt.markav.core.style.IaClass
import lt.markav.core.style.Mdl
import lt.markav.core.style.Mdl.ImplicitMaterialUpgrade
import org.scalajs.dom
import org.scalajs.dom.html.Element

import scalatags.JsDom
import scalatags.JsDom.all._
import scalatags.JsDom.all.header

object Template {

  private val main = typedTag[dom.html.Element]("main")

  private val headerRow =
    div(Mdl.layoutHeaderRow,
      span(Mdl.layoutTitle,
        b("InAdvisor")),
      div(Mdl.layoutSpacer))

  val Header: Element =
    header(Mdl.layoutHeader,
      headerRow)
      .material

  val HeaderFab: (JsDom.TypedTag[Element]) => Element = fabIcon =>
    header(Mdl.layoutHeader,
      headerRow,
      div(style := "position: relative",
        button(Mdl.button, Mdl.jsButton, Mdl.buttonFab, Mdl.jsRippleEffect, Mdl.buttonColored, Mdl.shadow4dp,
          style := "position: absolute; bottom: -28px; right: 28px;",
          PageIds.HeaderFab,
          fabIcon)))
      .material

  val Main: ((Frag)) => Element = contents =>
    main(Mdl.layoutContent, Mdl.colorGrey100,
      div(Mdl.grid, IaClass.content,
        contents))
      .material

}
