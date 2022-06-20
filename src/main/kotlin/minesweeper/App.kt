package minesweeper

import minesweeper.domain.MineSweeper
import minesweeper.dto.MineBoardResponse
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val height = InputView.height()
    val width = InputView.width()
    val mineCount = InputView.mineCount()

    val mineBoard = MineSweeper().mineBoard(
        height = height,
        width = width,
        mineCount = mineCount
    )

    val mineBoardResponse = MineBoardResponse.of(mineBoard, width)
    ResultView.printBoard(mineBoardResponse)
}
