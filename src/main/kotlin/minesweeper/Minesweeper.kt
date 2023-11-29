package minesweeper

import minesweeper.domain.Height
import minesweeper.domain.Width
import minesweeper.view.InputView

object Minesweeper {

    fun start() {
        InputView.height.let(::Height)
        InputView.width.let(::Width)
    }
}
