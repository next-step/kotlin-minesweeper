package controller

import domain.Cells
import domain.MineBoard
import domain.MineGameMetric
import domain.strategy.RandomMineCellGenerator
import view.InputView
import view.OutputView

class MineSweeperController {
    fun run() {
        val rowSize = InputView.inputRowSize()
        val colSize = InputView.inputColumnSize()
        val mineCount = InputView.inputMineCount()
        val mineGameMetric = MineGameMetric(mineCount, rowSize, colSize)

        val cells = Cells.generateWithMines(mineGameMetric, RandomMineCellGenerator())
        val mineBoard = MineBoard(mineGameMetric, cells)

        OutputView.showMineSweeperBoard(mineBoard)
    }
}
