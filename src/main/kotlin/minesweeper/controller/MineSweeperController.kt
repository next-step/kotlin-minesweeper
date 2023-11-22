package minesweeper.controller

import minesweeper.domain.GameStatus
import minesweeper.domain.MineGenerator
import minesweeper.domain.MineSweeperMap
import minesweeper.domain.MineSweeperResult
import minesweeper.view.InputView
import minesweeper.view.OutputView

class MineSweeperController {

    fun start() {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val mineCount = InputView.inputMines()
        val mineSweeperMap2 = MineGenerator.generate(height, width, mineCount)
        OutputView.printMineSweeperStart()

        while (mineSweeperMap2.openIndex() == GameStatus.CONTINUE) {
            OutputView.printMineSweeper(height.value, MineSweeperResult(mineSweeperMap2).resultByRow)
        }
        OutputView.printLoseMineSweeper(height.value, MineSweeperResult(mineSweeperMap2).resultByRow)
    }

    private fun MineSweeperMap.openIndex(): GameStatus {
        return open(InputView.inputOpenPosition())
    }
}
