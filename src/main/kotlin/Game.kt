import minesweeper.MinesweeperGame
import minesweeper.PlayStatus
import minesweeper.board.BoardElement
import minesweeper.board.render.DefaultBoardRender
import minesweeper.board.render.MinesweeperBoardRender
import minesweeper.position.Position
import minesweeper.position.RandomPosition
import minesweeper.view.Input
import minesweeper.view.Output
import java.lang.NumberFormatException

private const val ROW = 0
private const val COL = 1
private const val INPUT_SIZE = 2
private const val INIT_CELL = 'C'
private const val INIT_CELL_NUMBER = '0'
private const val INPUT_POSITION_DELIMITER = ", "

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

    val defaultGameBoard = DefaultBoardRender(mines)(boardElement, INIT_CELL)
    val minesweeperGameBoard = MinesweeperBoardRender(mines)(boardElement, INIT_CELL_NUMBER)

    val minesweeperGame = MinesweeperGame(defaultGameBoard, minesweeperGameBoard, boardElement)

    Output.printStartMessage()
    gameStart(minesweeperGame, boardElement)
}

private fun gameStart(minesweeperGame: MinesweeperGame, boardElement: BoardElement) {
    val position = convertStringToPosition(boardElement)
    when (minesweeperGame.play(position)) {
        PlayStatus.OPEN -> {
            Output.printAny(minesweeperGame.render())
            gameStart(minesweeperGame, boardElement)
        }
        PlayStatus.WIN -> Output.printWinGame()
        PlayStatus.LOSE -> Output.printLoseGame()
    }
}

private tailrec fun convertStringToPosition(
    boardElement: BoardElement
): Position {
    Output.printCellMessage()
    return toPosition(Input.getLine().split(INPUT_POSITION_DELIMITER), boardElement)
        ?: convertStringToPosition(boardElement)
}

private fun toPosition(split: List<String>, boardElement: BoardElement): Position? {
    if (split.size != INPUT_SIZE) {
        return null
    }
    try {
        val position = Position(split[COL], split[ROW])
        if (boardElement.isOutOfRange(position)) {
            return null
        }
        return position
    } catch (e: NumberFormatException) {
        return null
    }
}

private tailrec fun convertStringToInt(
    exceptionCallback: () -> Unit,
    predicates: List<(String) -> Boolean>
): Int {
    val line = Input.getLine()
    return when (predicates.all { it.invoke(line) }) {
        true -> line.toInt()
        false -> {
            exceptionCallback()
            convertStringToInt(exceptionCallback, predicates)
        }
    }
}

private fun isOnlyNumber(line: String): Boolean = line.all { it.isDigit() }

private fun isPositiveNumber(line: String): Boolean = line.toInt() > 0
