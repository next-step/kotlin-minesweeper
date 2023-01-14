package minesweeper.domain

data class CellPosition(private val xPosition: Position, private val yPosition: Position) : Comparable<CellPosition> {

    enum class NearPosition(
        val x: Int,
        val y: Int,
    ) {
        LEFT_UP(-1, +1),
        UP(0, +1),
        RIGHT_UP(+1, +1),
        RIGHT(+1, 0),
        RIGHT_DOWN(+1, -1),
        DOWN(0, -1),
        LEFT_DOWN(-1, -1),
        LEFT(-1, 0);
    }

    fun getNear(): List<CellPosition> =
        NearPosition.values().map { nearPosition ->
            val nearXPosition = Position(nearPosition.x)
            val nearYPosition = Position(nearPosition.y)

            CellPosition(nearXPosition, nearYPosition)
        }

    override fun compareTo(other: CellPosition): Int =
        when {
            this.xPosition > other.xPosition -> 1
            this.xPosition == other.xPosition && this.yPosition > other.yPosition -> 1
            else -> -1
        }
}
