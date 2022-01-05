package minesweeper.controller

import minesweeper.domain.Board
import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Position
import minesweeper.domain.Width
import minesweeper.domain.state.Running
import minesweeper.domain.state.State
import minesweeper.view.InputView
import minesweeper.view.OutputView

object MinesweeperController {

    fun start() {
        val width = Width.from(InputView.inputWidth())
        val height = Height.from(InputView.inputHeight())
        val mineCount = MineCount.from(InputView.inputMineCount())
        startGame(width, height, mineCount)
    }

    private fun startGame(width: Width, height: Height, mineCount: MineCount) {
        OutputView.printStartGame()
        val board = Board.ofSizeAndMineCount(width, height, mineCount)
        var state: State = Running(board)
        OutputView.printMineSweeper(state.board)
        while (!state.isFinished()) {
            state = state.open(Position.from(InputView.inputOpen()))
            OutputView.printMineSweeper(state.board)
        }
        OutputView.printResult(state)
    }
}
