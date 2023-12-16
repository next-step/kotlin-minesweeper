import minesweeper.Element
import minesweeper.view.Input
import minesweeper.view.Output

fun main() {
    Output.printHeightMessage()
    val number: Element = convertStringToElement(Output::printInputOnlyDigit)

    Output.printWidthMessage()
    val width: Element = convertStringToElement(Output::printInputOnlyDigit)

    Output.printMinesMessage()
    val mineCount: Element = convertStringToElement(Output::printInputOnlyDigit)


}

private tailrec fun convertStringToElement(exceptionCallback: () -> Unit): Element {
    val line = Input.getLine()
    return when (line.isOnlyDigit()) {
        true -> Element(line.toInt())
        false -> {
            exceptionCallback.invoke()
            convertStringToElement(exceptionCallback)
        }
    }
}

private fun String.isOnlyDigit(): Boolean = this.all { it.isDigit() }
