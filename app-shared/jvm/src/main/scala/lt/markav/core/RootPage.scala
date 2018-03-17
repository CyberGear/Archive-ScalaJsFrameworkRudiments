package lt.markav.core

import lt.markav.core.utils.PrettyHtml.HtmlFormatter

import scalacss.DevDefaults._
import scalacss.ScalatagsCss._
import scalatags.Text.all._
import scalatags.Text.tags2

class RootPage {

  def page: String =
    html(
      head(
        tags2.title("InAdvisor"),
        script(src := "inadvisor-jsdeps.js"),
        script(attr("defer") := true, src := "inadvisor-fastopt.js")
      ),
      body(
        Template.body,
        div(PageIds.MainContainer)
      )
    ).render.prettyHtml

}
