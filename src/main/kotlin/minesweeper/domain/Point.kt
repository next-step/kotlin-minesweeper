package minesweeper.domain

data class Point(val x: Int, val y: Int) : Comparable<Point> {

    init {
        if (x < 0 || y < 0) {
            throw MineSweeperException(ExceptionReason.NEGATIVE_POINT_VALUE)
        }
    }

    override fun compareTo(other: Point) = compareValuesBy(this, other, { it.y }, { it.x })
}
