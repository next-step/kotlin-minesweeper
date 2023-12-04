package minesweeper

import minesweeper.domain.MineCount
import minesweeper.domain.RandomPositionPicker
import minesweeper.domain.field
import minesweeper.domain.field.Field
import minesweeper.domain.field.Height
import minesweeper.domain.field.Width
import minesweeper.view.FieldView
import minesweeper.view.InputView
import minesweeper.view.OutputView

object MinesweeperController {

    fun start() {
        val field = field(RandomPositionPicker()) {
            size(height, width)
            mineCount(mineCount)
        }
        showField(field)
    }

    private val height: Height = InputView.height.let(::Height)
    private val width: Width = InputView.width.let(::Width)
    private val mineCount: MineCount = InputView.mineCount.let(::MineCount)

    private fun showField(field: Field) {
        OutputView.drawField(FieldView.from(field))
    }
}
