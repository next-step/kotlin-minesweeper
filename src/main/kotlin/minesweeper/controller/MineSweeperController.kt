package minesweeper.controller

import minesweeper.domain.GameStatus
import minesweeper.domain.MineGenerator
import minesweeper.domain.MineSweeperMap
import minesweeper.domain.MineSweeperResult
import minesweeper.tdddomain.MineSweeperMap2
import minesweeper.view.InputView
import minesweeper.view.OutputView

class MineSweeperController {

    fun start() {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val mineCount = InputView.inputMines()
        val mineSweeperMap = MineSweeperMap(height, width)
        val mapIndexes = mineSweeperMap.createPosition()
        val mines = MineGenerator.generate(mapIndexes, mineCount)
        val mineSweeperMap2 = MineGenerator.generate(height, width, mineCount)
        OutputView.printMineSweeperStart()

        while (mineSweeperMap2.openIndex() == GameStatus.CONTINUE) {
            OutputView.printMineSweeper(height.value, MineSweeperResult(mineSweeperMap2).resultByRow)
        }
        OutputView.printLoseMineSweeper(height.value, MineSweeperResult(mineSweeperMap2).resultByRow)
    }

    private fun MineSweeperMap2.openIndex(): GameStatus {
        return open(InputView.inputOpenPosition())
    }
}
