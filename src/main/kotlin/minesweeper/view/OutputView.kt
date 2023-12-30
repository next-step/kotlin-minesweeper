package minesweeper.view

import minesweeper.controller.OutputConsumer
import minesweeper.domain.board.MineBoard
import minesweeper.domain.game.GameResult

class OutputView : OutputConsumer {
    override fun showBoard(board: MineBoard) {
        BoardView.from(board).forEach {
            println(it)
        }
    }

    override fun showGameResult(result: GameResult) {
        val message = when (result) {
            GameResult.WIN -> WIN_RESULT
            GameResult.LOSS -> LOSE_RESULT
        }
        println(message)
    }

    companion object {
        private const val WIN_RESULT = "WIN GAME"
        private const val LOSE_RESULT = "LOSE GAME"
    }
}
