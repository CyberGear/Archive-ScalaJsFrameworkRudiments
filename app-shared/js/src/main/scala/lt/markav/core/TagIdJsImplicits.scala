package lt.markav.core

import scala.language.implicitConversions
import scalatags.JsDom

trait TagIdJsImplicits {

  implicit final def unboxToJsDomAttr(tid: TagId): JsDom.Modifier = builder => {
    builder.id = tid.id
  }

}
