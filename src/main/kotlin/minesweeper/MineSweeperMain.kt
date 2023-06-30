package minesweeper

import minesweeper.domain.board.BoardRange
import minesweeper.view.InputView

fun main() {
    val inputView = InputView()

    val height = inputView.readHeight()
    val width = inputView.readWidth()

    val boardRange = BoardRange(height = height, width = width)

    val mineQuantity = inputView.readMineQuantity()
}
