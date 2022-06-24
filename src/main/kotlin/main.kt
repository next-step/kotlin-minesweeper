import domain.geometric.Dimension
import ui.InputUI

fun main() {
    val width = InputUI.readWidth()
    val height = InputUI.readHeight()

    val dimension = Dimension(width = width, height = height)

    val numberOfMines = InputUI.readNumberOfMines()

    // val board = Board(Matrix(dimension, numberOfMines))

    // OutputUI.showBoard(board)
}
