package minesweeper

import minesweeper.domain.board.Height
import minesweeper.domain.board.MineBoard
import minesweeper.domain.board.MineCount
import minesweeper.domain.board.RandomPositionPicker
import minesweeper.domain.board.Width
import minesweeper.domain.board.mineBoard
import minesweeper.view.BoardView
import minesweeper.view.InputView
import minesweeper.view.OutputView

object MinesweeperController {

    fun start() {
        val board = mineBoard(RandomPositionPicker()) {
            size(height, width)
            mineCount(mineCount)
        }
        showBoard(board)
    }

    private val height: Height = InputView.height.let(::Height)
    private val width: Width = InputView.width.let(::Width)
    private val mineCount: MineCount = InputView.mineCount.let(::MineCount)

    private fun showBoard(board: MineBoard) {
        OutputView.drawBoard(BoardView.from(board))
    }
}
