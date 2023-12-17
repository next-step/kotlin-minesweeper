package minesweeper

import minesweeper.controller.InputProvider
import minesweeper.domain.board.Height
import minesweeper.domain.board.MineBoard
import minesweeper.domain.board.MineTotal
import minesweeper.domain.board.RandomPositionPicker
import minesweeper.domain.board.Width
import minesweeper.domain.board.mineBoard
import minesweeper.view.BoardView
import minesweeper.view.OutputView

class MinesweeperController(
    private val inputProvider: InputProvider,
) {
    fun start() {
        val height = inputProvider.height().let(::Height)
        val width = inputProvider.width().let(::Width)
        val mineCount = inputProvider.mineCount().let(::MineTotal)

        val board = mineBoard(RandomPositionPicker()) {
            size(height, width)
            mineCount(mineCount)
        }
        showBoard(board)
    }

    private fun showBoard(board: MineBoard) {
        OutputView.drawBoard(BoardView.from(board))
    }
}
