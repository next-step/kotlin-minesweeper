package minesweeper.application

import minesweeper.application.dto.MineFieldView
import minesweeper.application.dto.Row
import minesweeper.domain.MineField
import minesweeper.domain.RandomMineCoordinateGenerator
import minesweeper.domain.vo.Height
import minesweeper.domain.vo.NumberOfMine
import minesweeper.domain.vo.Width

class MineSweeper {

    fun createMineField(height: Height, width: Width, numberOfMine: NumberOfMine): MineFieldView {
        val mineField = MineField.create(
            height, width, numberOfMine,
            RandomMineCoordinateGenerator
        )

        return mineField.fields.values
            .chunked(width.value)
            .map { Row(it) }
            .let(::MineFieldView)
    }
}
