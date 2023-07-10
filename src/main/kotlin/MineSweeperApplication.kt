import controller.MineSweeperController
import domain.BoardSize
import domain.MineSweeperBoard
import domain.RandomMinePositionsGenerator

fun main() {
    val mineSweeperController = MineSweeperController()
    val height = mineSweeperController.inputHeight()
    println()
    val width = mineSweeperController.inputWidth()
    println()
    val mines = mineSweeperController.inputMines()
    println()

    val boardSize = BoardSize(height, width)
    val board = MineSweeperBoard(boardSize, RandomMinePositionsGenerator(boardSize, mines).generate())
    mineSweeperController.printStartMessage()
    mineSweeperController.printBoard(board)
}
