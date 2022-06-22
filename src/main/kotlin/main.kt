import domain.Board
import domain.Matrix
import domain.geometric.Dimension
import ui.InputUI
import ui.OutputUI

fun main() {
    val width = InputUI.readWidth()
    val height = InputUI.readHeight()

    val dimension = Dimension(width = width, height = height)

    val numberOfMines = InputUI.readNumberOfMines()

    val board = Board(Matrix(dimension, numberOfMines))

    OutputUI.showBoard(board)
}
