package mine.controller

import mine.Board
import mine.GameStatus
import mine.view.InputView
import mine.view.OutputView

object MineController {
    fun run() {
        createGame().start()
    }

    private fun createGame(): Board {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val mine = InputView.inputMineCount()
        return Board.createBoard(width, height, mine)
    }

    private fun Board.start() {
        OutputView.startGame()
        run {
            val status = InputView.inputPosition().clickedCell()
            when (status) {
                GameStatus.GAMEOVER -> OutputView.printLoseGame()
                GameStatus.WIN -> OutputView.printWinGame()
                else -> {
                    OutputView.printBoard(this)
                    start()
                }
            }
        }
    }
}
