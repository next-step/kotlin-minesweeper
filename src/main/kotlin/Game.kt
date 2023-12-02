import minesweeper.BoardDimensions
import minesweeper.DefaultBoard
import minesweeper.Height
import minesweeper.MineCount
import minesweeper.MineGenerator
import minesweeper.MinesweeperBoard
import minesweeper.NumberBoard
import minesweeper.RandomPosition
import minesweeper.Width
import view.Input
import view.Output

fun main() {
    Output.printHeightMessage()
    val height: Height = Height(Input.getLine())

    Output.printWidthMessage()
    val width: Width = Width(Input.getLine())

    Output.printMinesMessage()
    val mineCount: MineCount = MineCount(Input.getLine())

    val dimensions = BoardDimensions(height, width)

    val mines = MineGenerator(mineCount, RandomPosition(dimensions)).generate()
    val adminBoard = NumberBoard(dimensions)
    val playerBoard = DefaultBoard(dimensions)

    val minesweeperBoard = MinesweeperBoard(adminBoard, playerBoard, mines)

    Output.printStartMessage()
    do {
        Output.printCellMessage()
        val result = dimensions.stringToPosition(Input.getLine())
        result.onSuccess {
            minesweeperBoard.openCell(it)
            Output.printAny(minesweeperBoard.playerBoardRender())
        }.onFailure {
            Output.printAny(it.message)
        }
    } while (result.isSuccess)
}
