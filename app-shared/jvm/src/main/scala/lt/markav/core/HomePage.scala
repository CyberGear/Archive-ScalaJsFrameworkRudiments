package lt.markav.core

import lt.markav.core.PageIds
import lt.markav.core.utils.PrettyHtml.HtmlFormatter

import scalatags.Text.tags2
import scalatags.Text.all._
import scalacss.DevDefaults._
import scalacss.ScalatagsCss._

class HomePage {

  def page: String =
    html(
      head(
        tags2.title("InAdvisor"),
        script(src := "inadvisor-jsdeps.js"),
        script(attr("defer") := true, src := "inadvisor-fastopt.js"),
        Template.render
      ),
      body(
        Template.body,
        div(PageIds.Content, "some contents")
      )
    ).render.prettyHtml

}
