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

    Router.route(Path()).render.pageId should be("404")
  }

  it should "routes to home widget with empty path" in {
    val id = rndId
    implicit val widget: Widget = TestWidget(widgets = List(TestWidget(id)))

    Router.route(Path()).render.pageId should be(id)
  }

  it should "routes to home widget with 'home' path" in {
    val id = rndId
    val path: Path = "home"
    implicit val widget: Widget = TestWidget(widgets = List(TestWidget(id, path)))

    Router.route(path).render.pageId should be(id)
  }

  it should "routes to top widget witch match full path" in {
    val id1 = rndId
    val id2 = rndId
    val fullPath = Path("one") / "two" / "three"
    implicit val widget: Widget = TestWidget(widgets = List(
      buildBranch(id1, "one", "two", "three"),
      buildBranch(id2, fullPath)
    ))

    Router.route(fullPath).render.pageId should be(id2)
  }

  it should "routes to two level depth" in {
    val id = rndId
    val path = Path("one", "two")
    implicit val widget: Widget = TestWidget(widgets = List(
      buildBranch(id, path.parts.map(Path(_)): _*)
    ))
    warn(widget)
    val value = Router.route(path)
    warn(value.modifiers)

//    value.id should be(id)
  }

  /** */

  implicit class IdGetter(element: Element) {
    def pageId: String = {
      element.innerHTML
    }
  }

  private def rndId = UUID.randomUUID().toString.substring(0, 3)

  def warn(str: Any): Unit = {
    def color = (str: String) => Console.BLUE + str + Console.RESET

    println(s"[${color("warn")}] ${color(str.toString)}")
  }

}


case class TestWidget(wid: String = "",
                      override val path: Path = Path(),
                      override val widgets: List[Widget] = List.empty)
  extends Widget {
  override def contents: Tag =
    if (wid.isEmpty) {
      println(s"[info] $path")
      div("", page)
    }
    else {
      println(s"[info] $path($wid)")
      div(id:="pageId", wid)
    }

  override def toString: String = s"$path($wid) -> [${widgets.mkString(",")}]"
}

object TestWidget {

  def buildBranch(id: String, paths: Path*): Widget =
    paths.init.foldRight(TestWidget(id, paths.last))(
      (in, out) => TestWidget(path = in, widgets = List(out))
    )

}