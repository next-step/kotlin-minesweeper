package controller

import domain.Board
import domain.Coordinate
import domain.strategy.RandomMineCellGenerator
import view.InputView
import view.OutputView

class MineSweeperController {
    fun run() {
        val rowSize = InputView.inputRowSize()
        val colSize = InputView.inputColumnSize()
        val mineCount = InputView.inputMineCount()

        val board = Board(Coordinate(rowSize, colSize), mineCount = mineCount, RandomMineCellGenerator())
        val cells = board.create()

        OutputView.showMineSweeperBoard(cells)
    }
}
