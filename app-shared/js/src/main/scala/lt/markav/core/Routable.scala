package lt.markav.core

import org.scalajs.dom
import scalajs.js
import rx.Rx
import rx.Ctx

import scalatags.jsdom.Frag

trait Routable {
  val routes: List[Route]

  protected implicit val ctx: Ctx.Owner = Ctx.Owner.safe()

//  implicit def rxFrag[T <: Frag](r: Rx[T]): dom.Node = {
//    def renderSafe: dom.Node = r().render
//    var last = renderSafe
//
//    r.trigger {
//      val newLast = renderSafe
//      js.Dynamic.global.last = last
//      last.parentNode.replaceChild(newLast, last)
//      last = newLast
//    }
//
//    last
//  }
}
