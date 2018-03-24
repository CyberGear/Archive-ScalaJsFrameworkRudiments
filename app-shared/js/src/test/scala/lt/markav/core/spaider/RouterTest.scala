package lt.markav.core.spaider

import java.util.UUID

import lt.markav.core.spaider.TestWidget.buildBranch
import org.scalajs.dom.Element
import org.scalatest.FlatSpec
import org.scalatest.Matchers

import scalatags.JsDom
import scalatags.JsDom.all._

class RouterTest extends FlatSpec with Matchers {

  it should "routes to 404 when no widgets added" in {
    implicit val widget: Widget = TestWidget()

    Router.route(Path()).render.outerHTML should include ("404")
  }

  it should "routes to home widget with empty path" in {
    val id = rndId
    implicit val widget: Widget = TestWidget(widgets = List(TestWidget(id)))

    Router.route(Path()).render.outerHTML should include (id)
  }

  it should "routes to home widget with 'home' path" in {
    val id = rndId
    val path: Path = "home"
    implicit val widget: Widget = TestWidget(widgets = List(TestWidget(id, path)))

    Router.route(path).render.outerHTML should include (id)
  }

  it should "routes to top widget witch match full path" in {
    val id1 = rndId
    val id2 = rndId
    val fullPath = Path("one") / "two" / "three"
    implicit val widget: Widget = TestWidget(widgets = List(
      buildBranch(id1, "one", "two", "three"),
      buildBranch(id2, fullPath)
    ))

    Router.route(fullPath).render.outerHTML should include (id2)
  }

  it should "routes to two level depth" in {
    val id = rndId
    val path = Path("one", "two")
    implicit val widget: Widget = TestWidget(widgets = List(
      buildBranch(id, path.parts.map(Path(_)): _*)
    ))
    Router.route(path).render.outerHTML should include (id)
  }

  /** */

  private def rndId = UUID.randomUUID().toString.substring(0, 3)

}


case class TestWidget(wid: String = "",
                      override val path: Path = Path(),
                      override val widgets: List[Widget] = List.empty)
  extends Widget {
  override def contents: Tag =
    if (wid.isEmpty) div("", page)
    else div(wid)

  override def toString: String = s"$path($wid) -> [${widgets.mkString(",")}]"
}

object TestWidget {

  def buildBranch(id: String, paths: Path*): Widget =
    paths.init.foldRight(TestWidget(id, paths.last))(
      (in, out) => TestWidget(path = in, widgets = List(out))
    )

}