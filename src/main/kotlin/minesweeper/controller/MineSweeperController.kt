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
        val mineSweeperMap = MineGenerator.generate(height, width, mineCount)
        OutputView.printMineSweeperStart()

        while (mineSweeperMap.openIndex() == GameStatus.CONTINUE) {
            OutputView.printMineSweeper(height.value, MineSweeperResult(mineSweeperMap).resultByRow)
        }
        OutputView.printLoseMineSweeper(height.value, MineSweeperResult(mineSweeperMap).resultByRow)
    }

    private fun MineSweeperMap.openIndex(): GameStatus {
        return open(InputView.inputOpenPosition())
    }
}
