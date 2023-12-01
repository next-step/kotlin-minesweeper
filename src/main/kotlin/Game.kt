import minesweeper.BoardDimensions
import minesweeper.CharBoard
import minesweeper.GameBoard
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
    val numberBoard = NumberBoard(dimensions)
    val charBoard = CharBoard(dimensions)

    val minesweeperBoard = MinesweeperBoard(numberBoard, mines)

    Output.printStartMessage()
    Output.printBoard(minesweeperBoard.render())
}
