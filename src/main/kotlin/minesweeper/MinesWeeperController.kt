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

        val minesWeeperService = MinesWeeperService(
            height = height,
            width = width,
            mineCount = mineCount
        )
        val board = minesWeeperService.createCells()
        minesWeeperService.addMineAroundCounts()

        startGame(minesWeeperService, board)
    }

    private fun startGame(minesWeeperService: MinesWeeperService, board: Board) {
        resultView.printStartGame()
        while (!minesWeeperService.isFinishGame()) {
            if (openCell(minesWeeperService, board)) break
        }

        resultView.printWinGame()
    }

    private fun openCell(minesWeeperService: MinesWeeperService, board: Board): Boolean {
        val (row, column) = inputView.inputOpenCell()
        if (!minesWeeperService.openCells(row, column)) {
            resultView.printLoseGame()
            return true
        }

        resultView.printBoard(board)
        return false
    }
}

fun main() {
    MinesWeeperController(InputView(), ResultView()).run()
}
