import minesweeper.GameBoard
import minesweeper.Height
import minesweeper.MineCount
import minesweeper.MineGenerator
import minesweeper.MinesweeperBoard
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

    val gameBoard = GameBoard(height, width)
    val mines = MineGenerator(mineCount, RandomPosition(gameBoard)).generate()
    val minesweeperBoard = MinesweeperBoard(gameBoard, mines)

    Output.printStartMessage()
    Output.printMinesweeperBoard(minesweeperBoard)
}
