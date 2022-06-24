package view

import domain.Board
import domain.Dimension
import domain.Matrix

fun main() {
    val width = InputView.getWidth()
    val height = InputView.getHeight()

    val dimension = Dimension(width = width, height = height)

    val numberOfMines = InputView.getMinesCount()

    val board = Board(Matrix(dimension, numberOfMines))

    OutputView.showBoard(board)
}
