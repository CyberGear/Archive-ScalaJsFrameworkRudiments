package lt.markav.inadvisor

import autowire._
import lt.markav.core.api.{AccountApi, AlarmsApi}
import lt.markav.core.{PageIds, Rest, TagIdJsImplicits}
import org.scalajs.dom
import org.scalajs.jquery.jQuery

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scalatags.JsDom.all._

object Client extends App with TagIdJsImplicits {

  val inputBox = input.render
  val outputBox = ul.render

  jQuery(PageIds.Content).append(
    div(
      h1(PageIds.Label, "File Search"),
      inputBox,
      outputBox
    ).render
  )

  Rest[AccountApi].label().call().foreach(data => jQuery(PageIds.Label).text(data))

  inputBox.onkeyup = (e: dom.Event) => update()
  update()

  def update(): Unit = Rest[AlarmsApi].listAlarms("testUser").call().foreach { data =>
    data.foreach(item => outputBox.appendChild(li(item).render))
  }
}