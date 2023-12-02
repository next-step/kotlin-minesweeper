package minesweeper

import minesweeper.domain.MineCount
import minesweeper.domain.RandomPositionPicker
import minesweeper.domain.field
import minesweeper.domain.field.Field
import minesweeper.domain.field.FieldSize
import minesweeper.domain.field.Height
import minesweeper.domain.field.Width
import minesweeper.view.FieldView
import minesweeper.view.InputView
import minesweeper.view.OutputView

object Minesweeper {

    fun start() {
        val field = field(requestSize(), RandomPositionPicker()) {
            installMines(requestMineCount())
        }
        responseField(field)
    }

    private fun requestSize(): FieldSize {
        val height = InputView.height.let(::Height)
        val width = InputView.width.let(::Width)
        return FieldSize(height, width)
    }

    private fun requestMineCount(): MineCount =
        InputView.mineCount.let(::MineCount)

    private fun responseField(field: Field) {
        OutputView.drawField(FieldView.from(field))
    }
}
