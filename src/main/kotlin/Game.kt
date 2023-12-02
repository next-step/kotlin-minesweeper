import minesweeper.BoardDimensions
import minesweeper.CharBoard
import minesweeper.GameBoard
import minesweeper.Height
import minesweeper.MineCount
import minesweeper.MineGenerator
import minesweeper.MinesweeperBoard
import minesweeper.NumberBoard
import minesweeper.Position
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
    val numberBoard = NumberBoard(dimensions)

    val minesweeperBoard = MinesweeperBoard(numberBoard, mines)

    Output.printStartMessage()
    do {
        Output.printCellMessage()
        val result = dimensions.stringToPosition(Input.getLine())
        val position = result.getOrThrow()
    } while (result.isSuccess)

    Output.printBoard(minesweeperBoard.render())
}
