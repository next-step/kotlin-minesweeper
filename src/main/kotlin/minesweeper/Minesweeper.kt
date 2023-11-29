package minesweeper

import minesweeper.domain.field.Height
import minesweeper.domain.field.MineField
import minesweeper.domain.field.Width
import minesweeper.view.InputView

object Minesweeper {

    fun start() {
        val field = createField()
        InputView.mineCount
    }

    private fun createField(): MineField {
        val height = InputView.height.let(::Height)
        val width = InputView.width.let(::Width)
        return MineField.of(height, width)
    }
}
