import domain.Board
import domain.Matrix
import domain.geometric.Dimension
import ui.InputUI
import ui.OutputUI

fun main() {
    val width = InputUI.readWidth()
    val height = InputUI.readHeight()
    val numberOfMines = InputUI.readNumberOfMines()

    val dimension = Dimension(width = width, height = height)

    val board = Board(Matrix.of(dimension, numberOfMines))

    OutputUI.showBoard(board)
}
