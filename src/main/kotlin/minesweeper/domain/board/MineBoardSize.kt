package minesweeper.domain.board

import minesweeper.domain.position.Position

data class MineBoardSize(
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

data class Height(
    val value: Int
) {
    init {
        require(value > 0) { "높이는 0보다 커야 합니다" }
    }

    val rowRange: IntRange = 0 until value
}

data class Width(
    val value: Int
) {
    init {
        require(value > 0) { "너비는 0보다 커야 합니다" }
    }

    val columnRange: IntRange = 0 until value
}
