package lt.markav.core

import scala.language.implicitConversions
import scalacss.StyleA
import scalatags.Text
import scalatags.generic.Attr
import scalatags.text
import scalatags.text.Builder

trait PageIds

object PageIds extends PageIds {
  val MainContainer: TagId = "mainContainer"
}

