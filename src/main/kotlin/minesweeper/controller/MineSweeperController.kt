package minesweeper.controller

import minesweeper.model.Board
import minesweeper.model.Height
import minesweeper.model.MineCount
import minesweeper.model.Position
import minesweeper.model.Width
import minesweeper.state.Running
import minesweeper.state.State
import minesweeper.view.InputView
import minesweeper.view.OutputView

object MineSweeperController {

    private val inputView = InputView()
    private val outputView = OutputView()

    fun play() {
        var state = createInitialState()
        outputView.showGamePlay()
        while (!state.isFinished()) {
            val position = inputView.getOpenPosition()
            state = state.tryOpen(position)
            outputView.showGameState(state)
        }
    }

    private fun createInitialState(): State {
        val height = inputView.getHeight()
        val width = inputView.getWidth()
        val mineCount = inputView.getMineCount()

        val board = createBoard(width, height, mineCount)
        return Running(board)
    }

    private fun createBoard(width: Width, height: Height, mineCount: MineCount): Board {
        val size = width.value * height.value
        val positions = Position.list(width, height)
            .shuffled()
            .take(mineCount.value.coerceAtMost(size))

        var result = Board.create(width, height)
        positions.forEach { position -> result = result.mine(position) }
        return result
    }
}
