package minesweeper.domain

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.CellMark
import minesweeper.domain.cell.Position
import minesweeper.domain.field.Field
import minesweeper.domain.field.FieldSize

fun field(
    size: FieldSize,
    minePicker: PositionPicker,
    block: FieldBuilder.() -> Unit
): Field = FieldBuilder(size, minePicker).apply(block).build()

class FieldBuilder(
    private val size: FieldSize,
    private val minePicker: PositionPicker,
) {
    private val allPositions by lazy { size.allPositionsOfRowAndColumns }
    private lateinit var minePositions: Set<Position>
    fun installMines(count: MineCount) {
        minePositions = minePicker.pick(allPositions, count.value)
    }

    fun build(): Field =
        Field(createCells(minePositions))

    private fun createCells(minePositions: Set<Position>): Set<Cell> =
        allPositions.map { Cell(it, it.determineMark(minePositions)) }.toSet()

    private fun Position.determineMark(minePositions: Set<Position>): CellMark =
        if (this in minePositions) CellMark.MINE
        else CellMark.EMPTY
}
