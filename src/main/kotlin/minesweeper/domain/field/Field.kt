package minesweeper.domain.field

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Position

data class Field(
    val elements: Set<Cell>,
    val size: FieldSize,
) {
    companion object {
        fun of(height: Height, width: Width): Field {
            val size = FieldSize(height, width)
            val allPositions = size.allPositionsOfRowAndColumns
            return Field(createCells(allPositions), size)
        }

        private fun createCells(positions: Set<Position>): Set<Cell> =
            positions.map(::Cell).toSet()
    }
}
