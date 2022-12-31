package minesweeper.domain

data class CellPosition(private val xPosition: Position, private val yPosition: Position) : Comparable<CellPosition> {

    fun getNear(): List<CellPosition> =
        xPosition.getNear().flatMap { nearX ->
            yPosition.getNear().map { nearY ->
                CellPosition(nearX, nearY)
            }
        }

    override fun compareTo(other: CellPosition): Int =
        when {
            this.xPosition > other.xPosition -> 1
            this.xPosition == other.xPosition && this.yPosition > other.yPosition -> 1
            else -> -1
        }
}
