package mine_tdd.controller

import mine_tdd.Board
import mine_tdd.GameStatus
import mine_tdd.view.InputView
import mine_tdd.view.OutputView

object MineController {
    fun start() {
        createGame().start()
    }

    private fun createGame(): Board {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val mine = InputView.inputMine()
        return Board.createBoard(width, height, mine)
    }

    private fun Board.start() {
        OutputView.startGame()
        run {
            when (this.clickedItem(InputView.openPosition())) {
                GameStatus.OVER -> OutputView.printLoseGame()
                GameStatus.WIN -> OutputView.printWinGame()
                else -> {
                    OutputView.printBoard(this)
                    start()
                }
            }
        }
    }
}
