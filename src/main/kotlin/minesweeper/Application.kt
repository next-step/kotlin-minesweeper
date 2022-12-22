package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.Matrix
import minesweeper.io.Input
import minesweeper.io.Output

fun main() {
    val input = Input()
    val output = Output()

    val height = input.getHeight()
    val width = input.getWidth()

    val board = Board(Matrix.of(width, height), input.getMineCount())

    output.printBoard(board)
}
