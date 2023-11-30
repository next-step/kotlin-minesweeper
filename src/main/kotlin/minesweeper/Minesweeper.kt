package minesweeper

import minesweeper.domain.field.Field
import minesweeper.domain.field.Height
import minesweeper.domain.field.Width
import minesweeper.view.InputView

object Minesweeper {

    fun start() {
        createField()
        InputView.mineCount
    }

    private fun createField(): Field {
        val height = InputView.height.let(::Height)
        val width = InputView.width.let(::Width)
        return Field.of(height, width)
    }
}
