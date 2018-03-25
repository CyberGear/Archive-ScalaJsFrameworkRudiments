package lt.markav.core.spaider

import java.util.UUID

import lt.markav.core.spaider.TestWidget.buildBranch
import org.scalatest.FlatSpec
import org.scalatest.Matchers

import scala.scalajs.js.JavaScriptException
import scalatags.JsDom.all._

class RouterTest extends FlatSpec with Matchers {

  it should "route to 404 when no widgets added" in {
    implicit val context: Widget = TestWidget()

    Router.route(Path()).render.outerHTML should include("404")
  }

  it should "route to home widget with empty path" in {
    val id = rndId
    implicit val context: Widget = TestWidget(widgets = List(TestWidget(id)))

    Router.route(Path()).render.outerHTML should include(id)
  }

  it should "route to home widget with 'home' path" in {
    val id = rndId
    val path: Path = "home"
    implicit val context: Widget = TestWidget(widgets = List(TestWidget(id, path)))

    Router.route(path).render.outerHTML should include(id)
  }

  it should "route to top level widget deep path widget" in {
    val id2 = rndId
    val path = Path("one") / "two" / "three"
    implicit val context: Widget = TestWidget(widgets = List(
      buildBranch(id2, path)
    ))

    Router.route(path).render.outerHTML should include(id2)
  }

  it should "route to second level widget" in {
    val id = rndId
    val path = Path("one") / "two"
    implicit val context: Widget = TestWidget(widgets = List(
      buildBranch(id, path.parts.map(Path(_)): _*)
    ))

    Router.route(path).render.outerHTML should include(id)
  }

  it should "route to top level first" in {
    val id = rndId
    val path = Path("one") / "two" / "three" / "four"
    implicit val context: Widget = TestWidget(widgets = List(
      buildBranch("noOk", path.parts.map(Path(_)): _*),
      buildBranch(id, path)
    ))

    Router.route(path).render.outerHTML should include(id)
  }

  it should "not allow duplicated paths" in {
    implicit val context: Widget = TestWidget(widgets = List(
      TestWidget("foo1", "foo"),
      TestWidget("foo2", "foo")
    ))

    intercept[JavaScriptException](Router.route(Path("foo")))
  }

  it should "allow multiple empty paths (with different sub trees)" in {
    implicit val context: Widget = TestWidget(widgets = List(
      TestWidget(widgets = List(
        TestWidget("idOne", Path("one"))
      )),
      TestWidget(widgets = List(
        TestWidget("idTwo", Path("two"))
      ))
    ))

    Router.route(Path("two")) // should not throw exception
  }

  it should "route to widget to path throw empty path" in {
    val id = rndId
    val path: Path = "home"
    implicit val context: Widget = TestWidget(widgets = List(
      TestWidget(widgets = List(
        TestWidget(id, path)
      ))
    ))

    Router.route(path).render.outerHTML should include(id)
  }

  it should "route to widget to path throw right empty path" in {
    val id = rndId
    val path: Path = "home"
    implicit val context: Widget = TestWidget(widgets = List(
      TestWidget(widgets = List(
        TestWidget("noGo", "noGo")
      )),
      TestWidget(widgets = List(
        TestWidget(id, path)
      ))
    ))

    Router.route(path).render.outerHTML should include(id)
  }

  it should "throw exception if endpoints match inside empty path widgets" in {
    val path: Path = Path("one") / "two" / "three" / "four"
    implicit val context: Widget = TestWidget(widgets = List(
      TestWidget(widgets = List(
        buildBranch(rndId, path.parts.map(Path(_)): _*)
      )),
      TestWidget(widgets = List(
        buildBranch(rndId, path)
      ))
    ))

    intercept[JavaScriptException](Router.route(path).render.outerHTML)
  }

  it should "route to right widget throw empty paths" in {
    val id = rndId
    implicit val context: Widget = TestWidget(widgets = List(
      TestWidget(widgets = List(
        buildBranch(rndId, "a", "b")
      )),
      TestWidget(widgets = List(
        TestWidget(path = "c", widgets = List(
          TestWidget(widgets = List(
            TestWidget(id, "a")
          ))
        ))
      ))
    ))

    Router.route(Path("c") / "a").render.outerHTML should include (id)
  }

  /** */

  private def rndId = UUID.randomUUID().toString.substring(0, 3)

}


case class TestWidget(wid: String = "",
                      override val path: Path = Path(),
                      override val widgets: List[Widget] = List.empty)
  extends Widget {
  override def contents: Tag =
    if (wid.isEmpty) div(attr("path") := path.toString, "", page)
    else div(attr("path") := path.toString, wid)

  override def toString: String = s"$path($wid) -> [${widgets.mkString(",")}]"
}

object TestWidget {

  def buildBranch(id: String, paths: Path*): Widget =
    paths.init.foldRight(TestWidget(id, paths.last))(
      (in, out) => TestWidget(path = in, widgets = List(out))
    )

}