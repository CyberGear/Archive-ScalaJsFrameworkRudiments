package lt.markav.inadvisor.template

import lt.markav.core.style.MaterialIcons
import lt.markav.core.style.Mdl

import scalatags.JsDom.all._

object Template {

  val Header =
    header(Mdl.layoutHeader,
      div(Mdl.layoutHeaderRow,
        span(Mdl.layoutTitle,
          b("InAdvisor")),
        div(Mdl.layoutSpacer)),
      div(style := "position: relative",
        button(Mdl.button, Mdl.jsButton, Mdl.buttonFab, Mdl.jsRippleEffect, Mdl.buttonColored, Mdl.shadow4dp,
          style := "position: absolute; bottom: -28px; right: 28px;",
          MaterialIcons.add)))

}
