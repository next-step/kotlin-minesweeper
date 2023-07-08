import controller.MineSweeperController
import domain.BoardSize
import domain.MineSweeperBoard

fun main() {
    val mineSweeperController = MineSweeperController()
    val height = mineSweeperController.inputHeight()
    println()
    val width = mineSweeperController.inputWidth()
    println()
    val mines = mineSweeperController.inputMines()
    println()

    val board = MineSweeperBoard(BoardSize(height, width), mines)
    mineSweeperController.printStartMessage()
    println(board)
}
