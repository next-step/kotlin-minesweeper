package controller

import domain.Cells
import domain.MineBoard
import domain.MineGameMetric
import domain.MineSweeperGame
import domain.strategy.RandomMineCellGenerator
import view.InputView
import view.OutputView

class MineSweeperController {
    fun run() {
        val rowSize = InputView.inputRowSize()
        val colSize = InputView.inputColumnSize()
        val mineCount = InputView.inputMineCount()
        val mineGameMetric = MineGameMetric(boardHeightSize = rowSize, boardWidthSize = colSize, mineCount = mineCount)

        val cells = Cells.generateWithMines(mineGameMetric, RandomMineCellGenerator())
        val mineBoard = MineBoard(mineGameMetric, cells)
        OutputView.showMineSweeperBoard(mineBoard)

        val mineSweeperGame = MineSweeperGame(mineBoard)
        gameLoop(mineSweeperGame)
    }

    private fun gameLoop(mineSweeperGame: MineSweeperGame) {
        while (mineSweeperGame.isContinueGame()) {
            val coordinate = InputView.askMineCoordinate()
            mineSweeperGame.openAdjacentCell(coordinate)
        }
    }
}
