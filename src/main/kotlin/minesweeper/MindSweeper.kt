package minesweeper

import minesweeper.domain.BoardGenerator
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MindSweeper {
    fun start() {
        val board = InputView.inputBoardInfo().let { (height, width, mineCount) ->
            BoardGenerator.create(height, width, mineCount)
        }
        ResultView.printBoard(board)
    }
}
