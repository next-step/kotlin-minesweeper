package minesweeper

import minesweeper.domain.MineCount
import minesweeper.domain.RandomPositionPicker
import minesweeper.domain.field
import minesweeper.domain.field.FieldSize
import minesweeper.domain.field.Height
import minesweeper.domain.field.Width
import minesweeper.view.InputView

object Minesweeper {

    fun start() {
        field(requestSize(), RandomPositionPicker()) {
            installMines(requestMineCount())
        }
    }

    private fun requestSize(): FieldSize {
        val height = InputView.height.let(::Height)
        val width = InputView.width.let(::Width)
        return FieldSize(height, width)
    }

    private fun requestMineCount(): MineCount =
        InputView.mineCount.let(::MineCount)
}
