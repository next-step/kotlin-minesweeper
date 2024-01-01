package minesweeper.domain.position

data class Position(
    val row: Int,
    val column: Int,
) {
    init {
        require(row >= MIN_ROW) { "행은 ${MIN_ROW}이상만 가능합니다" }
        require(column >= MIN_COLUMN) { "열은 ${MIN_COLUMN}이상만 가능합니다" }
    }

    val adjacentPositions: Set<Position>
        get() = setOfNotNull(
            createIfValid(row - 1, column - 1),
            createIfValid(row - 1, column),
            createIfValid(row - 1, column + 1),
            createIfValid(row, column - 1),
            createIfValid(row, column + 1),
            createIfValid(row + 1, column - 1),
            createIfValid(row + 1, column),
            createIfValid(row + 1, column + 1),
        )

    companion object {
        private const val MIN_ROW = 0
        private const val MIN_COLUMN = 0

        private fun createIfValid(row: Int, column: Int): Position? =
            takeIf { row >= MIN_ROW && column >= MIN_COLUMN }?.let { Position(row, column) }
    }
}
