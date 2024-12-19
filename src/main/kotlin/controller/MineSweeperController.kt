package controller

import domain.Coordinate
import domain.MineBoard
import domain.strategy.RandomMineCellGenerator
import view.InputView
import view.OutputView

class MineSweeperController {
    fun run() {
        val rowSize = InputView.inputRowSize()
        val colSize = InputView.inputColumnSize()
        val mineCount = InputView.inputMineCount()

        val mineBoard = MineBoard(Coordinate(rowSize, colSize), mineCount = mineCount, RandomMineCellGenerator())
        val cells = mineBoard.create()

        OutputView.showMineSweeperBoard(cells)
    }
}
