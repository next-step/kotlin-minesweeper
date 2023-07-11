package minesweeper

import minesweeper.domain.BoardGenerator
import minesweeper.domain.Result
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MindSweeper {
    fun start() {
        val board = InputView.inputBoardInfo().let {
            BoardGenerator.createBoard(it)
        }
        ResultView.printGameStart()
        while (true) {
            val inputPoint = InputView.inputPoint()
            val result = board.openCell(inputPoint)
            ResultView.printBoard(board)
            if (result == Result.LOSE) {
                ResultView.printLose()
                break
            }
        }
    }
}
