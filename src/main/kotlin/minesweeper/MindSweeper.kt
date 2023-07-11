package minesweeper

import minesweeper.domain.BoardGenerator
import minesweeper.domain.MineBoard
import minesweeper.domain.Result
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MindSweeper {
    fun start() {
        val board = InputView.inputBoardInfo().let {
            BoardGenerator.createBoard(it)
        }
        ResultView.printGameStart()
        playingGame(board)
    }

    private fun playingGame(board: MineBoard) {
        while (true) {
            val result = playTurn(board)
            ResultView.printBoard(board)
            if (result != Result.CONTINUE) {
                printEndGame(result)
                break
            }
        }
    }

    private fun playTurn(board: MineBoard): Result {
        val inputPoint = InputView.inputPoint()
        return board.openCell(inputPoint)
    }

    private fun printEndGame(result: Result) {
        when (result) {
            Result.LOSE -> ResultView.printLose()
            Result.WIN -> ResultView.printWin()
            else -> throw IllegalArgumentException("지원하지 않는 결과입니다.")
        }
    }
}
