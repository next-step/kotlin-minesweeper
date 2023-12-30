package minesweeper

import minesweeper.controller.InputProvider
import minesweeper.controller.OutputConsumer
import minesweeper.domain.board.Height
import minesweeper.domain.board.MineBoard
import minesweeper.domain.board.MineTotal
import minesweeper.domain.board.RandomPositionPicker
import minesweeper.domain.board.Width
import minesweeper.domain.board.mineBoard
import minesweeper.domain.cell.Position
import minesweeper.domain.game.MinesweeperGame

class MinesweeperController(
    private val inputProvider: InputProvider,
    private val outputProvider: OutputConsumer,
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
            size(width * height)
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
            showGame(game)
        }
    }

    private fun showGame(game: MinesweeperGame) {
        val result = game.result
        if (result != null) outputProvider.showGameResult(result)
        else outputProvider.showBoard(game.board)
    }
}
