package minesweeper

import minesweeper.domain.Board
import minesweeper.io.Input
import minesweeper.io.Output

fun main() {
    val input = Input()
    val output = Output()

    val height = input.getHeight()
    val width = input.getWidth()

    val board = Board.of(width, height, input.getMineCount())

    while (!board.isLose()) {
        board.open(input.getCoordinate())
        output.printBoard(board)

        if (board.isWin()) break
    }
}
