import org.scalatra._
import javax.servlet.ServletContext

import lt.markav.inadvisor.HomePageServlet

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new HomePageServlet, "/*")
  }
}
