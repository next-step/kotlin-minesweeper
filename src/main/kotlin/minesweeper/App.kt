package minesweeper

import minesweeper.domain.MineSweeperFactory
import minesweeper.dto.MineSweeperResponse
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val height = InputView.height()
    val width = InputView.width()
    val mineCount = InputView.mineCount()

    val mineSweeper = MineSweeperFactory().mineSweeper(
        height = height,
        width = width,
        mineCount = mineCount
    )

    ResultView.printGameStart()

    while (!mineSweeper.isEnd) {
        val coordinate = InputView.open()
        mineSweeper.open(coordinate)
        ResultView.printMineSweeper(MineSweeperResponse.of(mineSweeper.boardFields, width))
    }
    ResultView.printGameResult(mineSweeper.isWin)
}
