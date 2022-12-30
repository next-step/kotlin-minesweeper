package minesweeper.domain

data class Point(val x: Int, val y: Int) : Comparable<Point> {

    init {
        if (x < 0 || y < 0) {
            throw MineSweeperException(ExceptionReason.NEGATIVE_POINT_VALUE)
        }
    }

    fun getNearPoints(): List<Point> {
        return nearPoints.mapNotNull { plusOrNull(it.first, it.second) }
    }

    override fun compareTo(other: Point) = compareValuesBy(this, other, { it.y }, { it.x })

    private fun plusOrNull(x: Int, y: Int): Point? {
        return try {
            Point(this.x + x, this.y + y)
        } catch (e: Exception) {
            null
        }
    }

    companion object {
        private val nearPoints = listOf(
            -1 to -1,
            -1 to 0,
            -1 to 1,
            0 to -1,
            0 to 1,
            1 to -1,
            1 to 0,
            1 to 1
        )
    }
}
