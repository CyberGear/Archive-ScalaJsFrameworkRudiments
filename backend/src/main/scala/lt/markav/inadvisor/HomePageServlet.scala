package lt.markav.inadvisor

import org.scalatra._

class HomePageServlet extends ScalatraServlet {

  //this is home page

  get("/") {
    views.html.hello()
  }

}
