package minesweeper

import minesweeper.model.Board
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MinesWeeperController(
    private val inputView: InputView,
    private val resultView: ResultView,
) {
    fun run() {
        val height = inputView.inputHeight()
        val width = inputView.inputWidth()
        val mineCount = inputView.inputMineCount()

        val minesWeeperService = MinesWeeperService(height, width, mineCount)
        val board = minesWeeperService.createCells()
        minesWeeperService.addMineAroundCounts()

        startGame(minesWeeperService, board)
    }

    private fun startGame(minesWeeperService: MinesWeeperService, board: Board) {
        resultView.printStartGame()
        while (!minesWeeperService.isFinishGame()) {
            val (row, column) = inputView.inputOpenCell()
            if (!minesWeeperService.openCells(row, column)) {
                resultView.printLoseGame()
                break
            }

            resultView.printBoard(board)
        }

        resultView.printWinGame()
    }
}

fun main() {
    MinesWeeperController(InputView(), ResultView()).run()
}
