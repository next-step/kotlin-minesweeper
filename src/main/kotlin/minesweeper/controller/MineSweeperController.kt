package minesweeper.controller

import minesweeper.domain.GameBoard
import minesweeper.domain.GameBoardSize
import minesweeper.view.InputView
import minesweeper.view.OutputView

class MineSweeperController {

    fun execute() {
        val width = InputView.width()
        val height = InputView.height()
        val mineCount = InputView.mine()
        val gameBoard = GameBoard.of(GameBoardSize(width, height), mineCount)
        OutputView.printGameBoard(gameBoard.cells)
    }
}
