package lt.markav.inadvisor.js

import org.scalajs.jquery.jQuery

import scala.scalajs.js.annotation.JSExportTopLevel

object TutorialApp extends App {

  jQuery(() => setupUI())

  def appendPar(targetTag: String, text: String): Unit =
    jQuery(targetTag).append(s"<p>$text</p>")

  def addClickedMessage(): Unit = {
    appendPar("body", "You clicked the button!")
  }

  def setupUI(): Unit = {
    jQuery("#click-me-button").click(() => addClickedMessage())
    jQuery("body").append("<p>Hello World ;)</p>")
  }

}
