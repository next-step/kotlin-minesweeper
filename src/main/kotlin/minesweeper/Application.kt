package minesweeper

import minesweeper.domain.board.BoardFactory
import minesweeper.view.inputHeight
import minesweeper.view.inputMineCount
import minesweeper.view.inputWidth
import minesweeper.view.printBoard

fun main() {
    val height = inputHeight()
    val width = inputWidth()

    val board = BoardFactory.create(
        height = height,
        width = width,
        mineCount = inputMineCount()
    )
    printBoard(height, width, board)
}
