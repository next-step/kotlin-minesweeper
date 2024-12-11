package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.BoardConfig
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MineSweeperApplication {

    fun run() {
        val rowSize = InputView.inputRowSize()
        val colSize = InputView.inputColumnSize()
        val mineCount = InputView.inputMineCount()
        val boardConfig = BoardConfig(rowSize, colSize, mineCount)
        val board = Board(boardConfig)

        ResultView.printBoard(board)
    }
}
