import controller.MineSweeperController
import domain.Board

fun main() {
    val mineSweeperController = MineSweeperController()
    val height = mineSweeperController.inputHeight()
    println()
    val width = mineSweeperController.inputWidth()
    println()
    val mines = mineSweeperController.inputMines()
    println()

    val board = Board(height, width, mines)
}
