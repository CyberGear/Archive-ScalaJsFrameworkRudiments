package lt.markav.core.utils

import org.htmlcleaner.{HtmlCleaner, PrettyHtmlSerializer}

object PrettyHtml {

  val Cleaner = new HtmlCleaner()
  val Pretty = new PrettyHtmlSerializer(Cleaner.getProperties, "  ")

  implicit class HtmlFormatter(html: String) {
    def prettyHtml: String = Pretty.getAsString(Cleaner.clean(html))
  }

}
