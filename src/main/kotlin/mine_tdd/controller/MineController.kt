package mine_tdd.controller

import mine_tdd.Board
import mine_tdd.view.InputView
import mine_tdd.view.OutputView

object MineController {
    fun start() {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val mine = InputView.inputMine()

        val board = Board.createBoard(width, height, mine)
        OutputView.printBoard(board)
    }
}
