package minesweeper.domain.field

import minesweeper.domain.cell.Cell

class MineField(
    val height: Height,
    val width: Width,
    val elements: Set<Cell>,
) {
    companion object {
        fun of(height: Height, width: Width): MineField = MineField(
            height,
            width,
            createCells(height, width)
        )

        private fun createCells(height: Height, width: Width): Set<Cell> =
            height.rowRange
                .flatMap { row -> width createCellForColumnsInRow row }
                .toSet()

        private infix fun Width.createCellForColumnsInRow(row: Int): List<Cell> =
            this.columnRange.map { Cell(row = row, column = it) }
    }
}
