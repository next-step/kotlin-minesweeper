package minesweeper

import minesweeper.domain.Board
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MineSweeperApplication {

    fun run() {
        val rowSize = InputView.inputRowSize()
        val colSize = InputView.inputColumnSize()
        val mineCount = InputView.inputMineCount()

        val board = Board(rowSize, colSize, mineCount)

        ResultView.printBoard(board)
    }
}
