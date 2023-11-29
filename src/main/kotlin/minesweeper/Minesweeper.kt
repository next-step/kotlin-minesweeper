package minesweeper

import minesweeper.domain.Height
import minesweeper.view.InputView

object Minesweeper {

    fun start() {
        InputView.height.let(::Height)
    }
}
