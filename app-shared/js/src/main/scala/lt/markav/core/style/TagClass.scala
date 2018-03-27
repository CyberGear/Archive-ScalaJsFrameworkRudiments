package lt.markav.core.style

import scalatags.JsDom

case class TagClass(`class`: String)

object TagClass {

  implicit def boxToTagClass(cls: String): TagClass = TagClass(cls)

  implicit final def unboxToTextAttr(cls: TagClass): JsDom.all.Modifier = builder => {
    builder.classList.add(cls.`class`)
  }
}
