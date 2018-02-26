package lt.markav.inadvisor

import org.scalatra.test.scalatest._

class HomePageServletTests extends ScalatraFunSuite {

  addServlet(classOf[HomePageServlet], "/*")

  test("GET / on MyScalatraServlet should return status 200"){
    get("/"){
      status should equal (200)
    }
  }

}
