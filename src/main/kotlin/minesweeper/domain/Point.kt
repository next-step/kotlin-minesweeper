package minesweeper.domain

data class Point(val x: Int, val y: Int) : Comparable<Point> {

    init {
        if (x < 0 || y < 0) {
            throw MineSweeperException(ExceptionReason.NEGATIVE_POINT_VALUE)
        }
    }

    fun getNearPoints(width: Int, height: Int): List<Point> {
        val nearPoints = nearPoints.toList()
        return nearPoints.mapNotNull { plusOrNull(it.first, it.second, width, height) }
    }

    override fun compareTo(other: Point) = compareValuesBy(this, other, { it.y }, { it.x })

    private fun plusOrNull(x: Int, y: Int, maxX: Int, maxY: Int): Point? {
        if (isValidateX(x, maxX) && isValidateY(y, maxY)) {
            return Point(this.x + x, this.y + y)
        }
        return null
    }

    private fun isValidateX(x: Int, maxX: Int): Boolean {
        return (this.x + x) in 0 until maxX
    }

    private fun isValidateY(y: Int, maxY: Int): Boolean {
        return (this.y + y) in 0 until maxY
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
