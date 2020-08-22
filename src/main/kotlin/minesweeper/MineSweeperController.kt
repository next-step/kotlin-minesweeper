package minesweeper

import minesweeper.domain.MineSweeperApp
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MineSweeperController(
    private val inputView: InputView = InputView,
    private val resultView: ResultView = ResultView
) {
    private val mineSweeperApp: MineSweeperApp = MineSweeperApp(inputView.getHeight(), inputView.getWidth())

    fun start() {
        initMine()
        showResult()
    }

    private fun initMine() {
        val mineCount = inputView.getMineCount(mineSweeperApp.getMaxMineCount())
        mineSweeperApp.initMine(mineCount)
    }

    private fun showResult() {
        resultView.showResult(mineSweeperApp.getResult())
    }
}

fun main() {
    MineSweeperController().start()
}
