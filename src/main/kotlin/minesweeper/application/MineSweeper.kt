package minesweeper.application

import minesweeper.application.dto.MineFieldView
import minesweeper.application.dto.Row
import minesweeper.domain.MineFieldFactory
import minesweeper.domain.RandomMineCoordinateGenerator
import minesweeper.domain.vo.Height
import minesweeper.domain.vo.Width

class MineSweeper {

    fun createMineField(height: Height, width: Width, numberOfMine: Int): MineFieldView {
        val mineField = MineFieldFactory.create(
            height, width, numberOfMine,
            RandomMineCoordinateGenerator
        )

        return mineField.fields.map { it.dot }
            .chunked(width.value)
            .map { Row(it) }
            .let(::MineFieldView)
    }
}
