import org.scalatra._
import javax.servlet.ServletContext

import lt.markav.inadvisor.{HomePageServlet, WebResourcesServlet}

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new HomePageServlet, "/*")
    context.mount(new WebResourcesServlet, "/web/*")
  }
}
