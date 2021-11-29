package mine.controller

import mine.Board
import mine.view.InputView
import mine.view.OutputView

object MineController {

    fun run() {
        OutputView.printBoard(createGame())
    }

    private fun createGame(): Board {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val mine = InputView.inputMineCount()

        return Board.createBoard(width, height, mine)
    }
}
