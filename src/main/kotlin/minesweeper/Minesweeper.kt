package minesweeper

import minesweeper.domain.RandomPositionPicker
import minesweeper.domain.field
import minesweeper.domain.field.FieldSize
import minesweeper.domain.field.Height
import minesweeper.domain.field.Width
import minesweeper.view.InputView

object Minesweeper {

    fun start() {
        field(createSize(), RandomPositionPicker()) {}
        InputView.mineCount
    }

    private fun createSize(): FieldSize {
        val height = InputView.height.let(::Height)
        val width = InputView.width.let(::Width)
        return FieldSize(height, width)
    }
}
