package lt.markav.core.framer

import org.scalajs.dom.Element
import org.scalajs.dom.Node
import rx._

import scalatags.JsDom.TypedTag
import scalatags.JsDom.all._

object TagsImplicits {

  implicit def typedTagRxToFrag(rx: Rx[TypedTag[_ <: Node]])(implicit ownerCtx: Ctx.Owner): Frag = new Frag {
    override def render: Node = rx.now.render

    override def applyTo(t: Element): Unit = {
      var oldChild: Option[Node] = None
      rx.trigger {
        val newChild = render
        oldChild match {
          case None => t.appendChild(newChild)
          case Some(old) => t.replaceChild(newChild, old)
        }
        oldChild = Some(newChild)
      }
    }
  }

}
