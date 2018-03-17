package lt.markav.core

import scalatags.Text
import scalatags.text.Builder

case class TagId(id: String)

object TagId {

  implicit def boxToPageId(id: String): TagId = TagId(id)

  implicit def unboxToId(pid: TagId): String = pid.id

  implicit final def unboxToTextAttr(pid: TagId): Text.all.Modifier = builder => {
    builder.setAttr("id", Builder.GenericAttrValueSource(pid.id))
  }

}