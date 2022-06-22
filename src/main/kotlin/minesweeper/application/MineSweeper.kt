package minesweeper.application

import minesweeper.application.dto.MineFieldView
import minesweeper.application.dto.Row
import minesweeper.domain.MineField
import minesweeper.domain.RandomMineCoordinateGenerator
import minesweeper.domain.field.Coordinate
import minesweeper.domain.field.Dot
import minesweeper.domain.vo.Height
import minesweeper.domain.vo.NumberOfMine
import minesweeper.domain.vo.Width

class MineSweeper {

    fun createMineField(height: Height, width: Width, numberOfMine: NumberOfMine): MineField =
        MineField.create(height, width, numberOfMine, RandomMineCoordinateGenerator)

    fun open(mineField: MineField, inputCoordinate: Coordinate): Dot = mineField.open(inputCoordinate)

    fun createMineFieldView(mineField: MineField, width: Width): MineFieldView =
        mineField.fields.values
            .chunked(width.value)
            .map(::Row)
            .let(::MineFieldView)
}
