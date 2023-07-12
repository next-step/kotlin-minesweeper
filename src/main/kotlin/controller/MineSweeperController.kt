package controller

import domain.MineBoard
import view.InputView
import view.OutputView

class MineSweeperController(
    private val inputView: InputView = InputView,
    private val outputView: OutputView = OutputView
) {

    fun start() {
        val boardSize = inputView.requestBoardSize()
        val mineCount = inputView.requestCountOfMine()

        outputView.printStartGame()
        val mineBoard = MineBoard(boardSize, mineCount)
        outputView.printMineBoard(mineBoard)
    }
}
