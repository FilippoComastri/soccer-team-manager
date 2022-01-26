import react.dom.render
import kotlinx.browser.document
import org.w3c.dom.Element

fun main() {
    render(document.getElementById("root") as Element) {
        child(app)
    }
}