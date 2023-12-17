import minesweeper.board.BoardElement
import minesweeper.board.render.MinesweeperBoardRender
import minesweeper.board.render.defaultBoardRender
import minesweeper.position.RandomPosition
import minesweeper.view.Input
import minesweeper.view.Output

private const val INIT_CELL = 'C'
private const val INIT_CELL_NUMBER = '0'

fun main() {
    val inputValidates = listOf(::isOnlyNumber, ::isPositiveNumber)

    Output.printHeightMessage()
    val height = convertStringToInt(Output::printInputValidate, inputValidates)

    Output.printWidthMessage()
    val width = convertStringToInt(Output::printInputValidate, inputValidates)

    Output.printMinesMessage()
    val mineCount = convertStringToInt(Output::printInputValidate, inputValidates)

    val boardElement = BoardElement(height, width)
    val mines = RandomPosition(boardElement).generate(mineCount)

    val defaultGameBoard = defaultBoardRender(boardElement, INIT_CELL)
    val minesweeperGameBoard = MinesweeperBoardRender(mines)(boardElement, INIT_CELL_NUMBER)

}

private tailrec fun convertStringToInt(
    exceptionCallback: () -> Unit,
    predicates: List<(String) -> Boolean>
): Int {
    val line = Input.getLine()
    return when (predicates.all { it.invoke(line) }) {
        true -> line.toInt()
        false -> {
            exceptionCallback.invoke()
            convertStringToInt(exceptionCallback, predicates)
        }
    }
}

private fun isOnlyNumber(line: String): Boolean = line.all { it.isDigit() }

private fun isPositiveNumber(line: String): Boolean = line.toInt() > 0
