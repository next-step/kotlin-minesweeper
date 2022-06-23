package minesweeper.domain.cell

data class Position(
    val index: Int,
    val x: Int,
    val y: Int
) {
    init {
        require(x >= 0 && y >= 0) { "property must be zero or positive." }
    }

    fun getNearbyPositions(width: Int, height: Int): Positions {
        val positions = NearbyDirection.values().mapNotNull { direction ->
            val size = width * height
            val nearbyX = direction.x + this.x
            val nearbyY = direction.y + this.y
            val nearbyIndex = nearbyY * width + nearbyX

            if (nearbyX.isBetweenRange(width) && nearbyY.isBetweenRange(height) && nearbyIndex < size) {
                Position(nearbyIndex, nearbyX, nearbyY)
            } else null
        }
        return Positions.from(positions)
    }

    private fun Int.isBetweenRange(limit: Int) = this in 0 until limit
}

class Positions private constructor(
    private val positions: List<Position>
) : List<Position> by positions {
    companion object {
        fun from(positions: List<Position>): Positions {
            return Positions(positions)
        }
    }
}
