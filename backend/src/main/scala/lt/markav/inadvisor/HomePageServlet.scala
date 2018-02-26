package lt.markav.inadvisor

import org.scalatra._

class HomePageServlet extends ScalatraServlet {

  get("/") {
    views.html.hello()
  }

}
