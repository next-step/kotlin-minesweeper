package minesweeper

import minesweeper.domain.MineCount
import minesweeper.domain.RandomPositionPicker
import minesweeper.domain.board
import minesweeper.domain.board.Board
import minesweeper.domain.board.Height
import minesweeper.domain.board.Width
import minesweeper.view.BoardView
import minesweeper.view.InputView
import minesweeper.view.OutputView

object MinesweeperController {

    fun start() {
        val board = board(RandomPositionPicker()) {
            size(height, width)
            mineCount(mineCount)
        }
        showBoard(board)
    }

    private val height: Height = InputView.height.let(::Height)
    private val width: Width = InputView.width.let(::Width)
    private val mineCount: MineCount = InputView.mineCount.let(::MineCount)

    private fun showBoard(field: Board) {
        OutputView.drawField(BoardView.from(field))
    }
}
