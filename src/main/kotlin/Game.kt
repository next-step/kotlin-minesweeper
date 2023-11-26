import minesweeper.GameBoard
import minesweeper.Message
import minesweeper.MineGenerator
import minesweeper.MinesweeperBoard
import minesweeper.RandomPosition
import view.Input
import view.Output
import kotlin.math.min

fun main() {
    Output.printAny(Message.INPUT_HEIGHT)
    val height: String = Input.getLine()

    Output.printAny(Message.INPUT_WIDTH)
    val width: String = Input.getLine()

    Output.printAny(Message.INPUT_MINES)
    val mineCount: String = Input.getLine()

    val gameBoard = GameBoard(height, width)
    val mines = MineGenerator(mineCount, RandomPosition(gameBoard)).generate()
    val minesweeperBoard = MinesweeperBoard(gameBoard, mines)

    Output.printMinesweeperBoard(minesweeperBoard)
}
