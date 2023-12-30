package minesweeper

import minesweeper.controller.InputProvider
import minesweeper.domain.board.Height
import minesweeper.domain.board.MineBoard
import minesweeper.domain.board.MineTotal
import minesweeper.domain.board.RandomPositionPicker
import minesweeper.domain.board.Width
import minesweeper.domain.board.mineBoard
import minesweeper.domain.cell.Position
import minesweeper.domain.game.MinesweeperGame
import minesweeper.view.BoardView
import minesweeper.view.OutputView

class MinesweeperController(
    private val inputProvider: InputProvider,
) {
    fun start() {
        val board = createBoard()
        val game = createGame(board)
        runGame(game)
    }

    private fun createBoard(): MineBoard {
        val height = inputProvider.height().let(::Height)
        val width = inputProvider.width().let(::Width)
        val mineCount = inputProvider.mineCount().let(::MineTotal)

        return mineBoard(RandomPositionPicker()) {
            size(height, width)
            mineCount(mineCount)
        }
    }

    private fun createGame(board: MineBoard): MinesweeperGame =
        MinesweeperGame(board) {
            inputProvider.openPosition().let {
                Position(
                    row = it.row,
                    column = it.column
                )
            }
        }

    private fun runGame(game: MinesweeperGame) {
        while (game.isEnd().not()) {
            game.run()
            showBoard(game.board)
        }
    }

    private fun showBoard(board: MineBoard) {
        OutputView.drawBoard(BoardView.from(board))
    }
}
