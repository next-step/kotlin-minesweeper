package minesweeper

import minesweeper.domain.MineSweeperFactory
import minesweeper.dto.MineBoardResponse
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val height = InputView.height()
    val width = InputView.width()
    val mineCount = InputView.mineCount()

    val mineBoard = MineSweeperFactory().mineSweeper(
        height = height,
        width = width,
        mineCount = mineCount
    )

    val mineBoardResponse = MineBoardResponse.of(mineBoard, width)
    ResultView.printGameStart()

    while (!mineBoard.isEnd) {
        val coordinate = InputView.open()
        mineBoard.open(coordinate)
        ResultView.printBoard(mineBoardResponse)
    }
    ResultView.printGameResult(mineBoard.isWin)
}
