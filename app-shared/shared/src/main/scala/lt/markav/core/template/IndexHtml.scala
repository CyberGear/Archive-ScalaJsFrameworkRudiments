package lt.markav.core.template

import lt.markav.core.PageIds
import lt.markav.core.style.Style

import scalacss.DevDefaults._
import scalacss.ScalatagsCss._
import scalatags.Text.all._
import scalatags.Text.tags2

class IndexHtml {

  def page: String =
    html(
      head(
        meta(charset := "utf-8"),
        meta(httpEquiv := "X-UA-Compatible", content := "IE=edge"),
        meta(name := "viewport", content := "width=device-width, initial-scale=1.0, minimum-scale=1.0"),
        meta(name := "mobile-web-app-capable", content := "yes"),

        link(rel := "stylesheet", href := "https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en"),
        link(rel := "stylesheet", href := "https://fonts.googleapis.com/icon?family=Material+Icons"),
        link(rel := "stylesheet", href := "https://code.getmdl.io/1.3.0/material.lime-blue.min.css"),

        tags2.title("InAdvisor"),
        script(attr("defer") := true, src := "inadvisor-jsdeps.js"),
        script(attr("defer") := true, src := "inadvisor-fastopt.js"),
        tags2.style(`type` := "text/css",
          """
            |html, body {
            |  font-family: 'Roboto', 'Helvetica', sans-serif;
            |}""".stripMargin),
        Style.render
      ),
      body(Style.body, PageIds.MainContainer)
    ).render

}
