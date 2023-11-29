package minesweeper

import minesweeper.domain.Height
import minesweeper.domain.MineField
import minesweeper.domain.Width
import minesweeper.view.InputView

object Minesweeper {

    fun start() {
        val height = InputView.height.let(::Height)
        val width = InputView.width.let(::Width)
        MineField(height, width)
        InputView.mineCount
    }
}
