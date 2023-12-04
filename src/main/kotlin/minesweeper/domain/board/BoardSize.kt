package minesweeper.domain.board

import minesweeper.domain.cell.Position

data class BoardSize(
    val height: Height,
    val width: Width,
) {
    val allPositionsOfRowAndColumns: Set<Position> by lazy {
        createAllPositions(height, width)
    }

    private fun createAllPositions(height: Height, width: Width): Set<Position> =
        height.rowRange
            .flatMap { row -> width createPositionForColumnsInRow row }
            .toSet()

    private infix fun Width.createPositionForColumnsInRow(row: Int): List<Position> =
        this.columnRange.map { Position(row = row, column = it) }
}
