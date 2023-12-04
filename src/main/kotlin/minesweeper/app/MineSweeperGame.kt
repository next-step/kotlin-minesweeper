package minesweeper.app

import minesweeper.model.board.Board
import minesweeper.view.CoordinateParser
import minesweeper.view.InputView
import minesweeper.view.OutputView

class MineSweeperGame(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun start(board: Board) {
        initialize()
        run(board)
        finalize()
    }

    private fun initialize() {
        outputView.gameStart()
    }

    private fun run(board: Board) {
        do {
            val status = board.tryOpen(inputView.openCoordinate(CoordinateParser))
            outputView.printMineMap(board)
        } while (GameStatus.ALIVE == status)
    }

    private fun finalize() {
        outputView.printGameResult()
    }
}
