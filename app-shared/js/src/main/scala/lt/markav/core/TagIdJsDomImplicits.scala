package lt.markav.core

import scalatags.JsDom

object TagIdJsDomImplicits {

  implicit final def unboxToJsDomAttr(tid: TagId): JsDom.all.Modifier = builder => {
    builder.id = tid.id
  }

}
