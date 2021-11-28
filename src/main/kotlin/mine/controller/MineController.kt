package mine.controller

import mine.Board
import mine.view.InputView

object MineController {

    fun run() {}
    fun createGame(): Board {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val mine = InputView.inputMineCount()

        return Board.createBoard(width, height, mine)
    }
}
