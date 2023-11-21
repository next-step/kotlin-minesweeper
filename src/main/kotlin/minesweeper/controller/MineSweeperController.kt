package minesweeper.controller

import minesweeper.domain.MineGenerator
import minesweeper.domain.MineStatus
import minesweeper.domain.MineSweeperIndexes
import minesweeper.domain.MineSweeperMap
import minesweeper.domain.MineSweeperResult
import minesweeper.domain.Mines
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
        OutputView.printMineSweeperStart()

        while (mapIndexes.openIndex(mines) == MineStatus.NOT_MINE) {
            OutputView.printMineSweeper(height.value, MineSweeperResult(mapIndexes, mines).resultByRow)
        }
        OutputView.printLoseMineSweeper(height.value, MineSweeperResult(mapIndexes, mines).resultByRow)
    }

    private fun MineSweeperIndexes.openIndex(mines: Mines): MineStatus {
        return open(mines, InputView.inputOpenPosition())
    }
}
