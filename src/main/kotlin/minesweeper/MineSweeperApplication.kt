package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.BoardConfig
import minesweeper.domain.OpenResult
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MineSweeperApplication {

    fun run() {
        val rowSize = InputView.inputRowSize()
        val colSize = InputView.inputColumnSize()
        val mineCount = InputView.inputMineCount()
        val boardConfig = BoardConfig(rowSize, colSize, mineCount)
        val board = Board(boardConfig)

        ResultView.startGame()

        while (true) {
            val (row, col) = InputView.inputOpenPosition()
            val result = board.open(row, col)
            ResultView.printBoard(board)

            when (result) {
                OpenResult.WIN -> {
                    ResultView.winGame()
                    return
                }

                OpenResult.LOSE -> {
                    ResultView.loseGame()
                    return
                }

                OpenResult.CONTINUE -> continue
            }
        }
    }
}
