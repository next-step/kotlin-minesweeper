import controller.MineSweeperController
import domain.BoardSize
import domain.MineSweeperBoard
import domain.MineSweeperBoardGenerator

fun main() {
    val mineSweeperController = MineSweeperController()
    val height = mineSweeperController.inputHeight()
    println()
    val width = mineSweeperController.inputWidth()
    println()
    val mines = mineSweeperController.inputMines()
    println()

    val board = MineSweeperBoardGenerator(BoardSize(height, width), mines).generate()
    mineSweeperController.printStartMessage()
    mineSweeperController.printBoard(board)
}
