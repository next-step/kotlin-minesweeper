import minesweeper.board.Element
import minesweeper.view.Input
import minesweeper.view.Output

fun main() {
    val inputValidates = listOf(::isOnlyNumber, ::isPositiveNumber)

    Output.printHeightMessage()
    val height: Element = convertStringToElement(Output::printInputValidate, inputValidates)

    Output.printWidthMessage()
    val width: Element = convertStringToElement(Output::printInputValidate, inputValidates)

    Output.printMinesMessage()
    val mineCount: Element = convertStringToElement(Output::printInputValidate, inputValidates)
}

private tailrec fun convertStringToElement(
    exceptionCallback: () -> Unit,
    predicates: List<(String) -> Boolean>
): Element {
    val line = Input.getLine()
    return when (predicates.all { it.invoke(line) }) {
        true -> Element(line.toInt())
        false -> {
            exceptionCallback.invoke()
            convertStringToElement(exceptionCallback, predicates)
        }
    }
}

private fun isOnlyNumber(line: String): Boolean = line.all { it.isDigit() }

private fun isPositiveNumber(line: String): Boolean = line.toInt() > 0
