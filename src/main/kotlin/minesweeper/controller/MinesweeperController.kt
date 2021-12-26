package minesweeper.controller

import minesweeper.domain.Board
import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Width
import minesweeper.view.InputView
import minesweeper.view.OutputView

object MinesweeperController {

    fun start() {
        val width = Width.from(InputView.inputWidth())
        val height = Height.from(InputView.inputHeight())
        val mineCount = MineCount.from(InputView.inputMineCount())
        val board = Board.of(width, height, mineCount)

        OutputView.printMineSweeper(board)
    }
}
