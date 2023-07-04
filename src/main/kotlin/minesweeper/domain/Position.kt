package minesweeper.domain

data class Position(
    val x: Int,
    val y: Int,
) {
    fun getAdjacentPositions(
        thresholdWidth: Int,
        thresholdHeight: Int,
    ): List<Position> {
        return ADJACENT_POSITIONS
            .map { Position(x + it.x, y + it.y) }
            .filter { it.isIn(thresholdHeight, thresholdWidth) }
    }

    private fun isIn(
        thresholdWidth: Int,
        thresholdHeight: Int,
    ): Boolean {
        return (x in 0 until thresholdWidth) &&
            (y in 0 until thresholdHeight)
    }

    companion object {
        private val ADJACENT_POSITIONS = listOf(
            Position(-1, -1),
            Position(-1, 0),
            Position(-1, 1),
            Position(0, -1),
            Position(0, 1),
            Position(1, -1),
            Position(1, 0),
            Position(1, 1),
        )
    }
}
