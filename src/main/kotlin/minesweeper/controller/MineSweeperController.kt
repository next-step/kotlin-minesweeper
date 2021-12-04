package minesweeper.controller

import minesweeper.model.Board
import minesweeper.model.Height
import minesweeper.model.MineCount
import minesweeper.model.Position
import minesweeper.model.Width
import minesweeper.view.InputView
import minesweeper.view.OutputView

object MineSweeperController {

    private val inputView = InputView()
    private val outputView = OutputView()

    fun play() {
        val height = inputView.getHeight()
        val width = inputView.getWidth()
        val mineCount = inputView.getMineCount()

        val board = createBoard(width, height, mineCount)
        outputView.showGamePlay(board)
    }

    private fun createBoard(width: Width, height: Height, mineCount: MineCount): Board {
        val size = width.value * height.value
        val positions = Position.list(width, height)
            .shuffled()
            .take(mineCount.value.coerceAtMost(size))

        return positions
            .fold(Board.create(width, height)) { acc, position -> acc.mine(position) }
    }
}
