package minesweeper.controller

import minesweeper.domain.Height
import minesweeper.domain.Width
import minesweeper.view.InputView

object MinesweeperController {

    fun start() {
        val width = Width.from(InputView.inputWidth())
        val height = Height.from(InputView.inputHeight())
    }
}
