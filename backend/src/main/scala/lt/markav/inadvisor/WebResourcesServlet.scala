package lt.markav.inadvisor

import org.fusesource.scalate.util.IOUtil
import org.scalatra.ScalatraServlet
import org.scalatra.scalate.ScalateSupport

class WebResourcesServlet extends ScalatraServlet with ScalateSupport {

  get("/*") {
    val param = params("splat")
    val resourcePath = s"/web/$param"
    Option(getClass.getResourceAsStream(resourcePath)) match {
      case Some(inputStream) =>
        contentType = servletContext.getMimeType(resourcePath)
        IOUtil.loadBytes(inputStream)
      case None => resourceNotFound()
    }
  }

}