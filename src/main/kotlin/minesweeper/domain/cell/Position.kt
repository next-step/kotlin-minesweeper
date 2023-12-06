package minesweeper.domain.cell

data class Position(
    val row: Int,
    val column: Int,
) {
    init {
        require(row >= MIN_ROW) { "행은 ${MIN_ROW}이상만 가능합니다" }
        require(column >= MIN_COLUMN) { "열은 ${MIN_COLUMN}이상만 가능합니다" }
    }

    val adjacentPositions: Set<Position>
        get() = movesToAdjacentPositions.mapNotNull { this.moveOrNull(it) }.toSet()

    private fun moveOrNull(move: Move): Position? {
        val row = this.row - move.row
        val column = this.column - move.column
        if (row < MIN_ROW || column < MIN_COLUMN) return null
        return Position(row, column)
    }

    companion object {
        private const val MIN_ROW = 0
        private const val MIN_COLUMN = 0
        private val movesToAdjacentPositions = listOf(
            Move(-1, -1),
            Move(-1, 0),
            Move(-1, 1),
            Move(0, -1),
            Move(0, 1),
            Move(1, -1),
            Move(1, 0),
            Move(1, 1),
        )
    }
}
