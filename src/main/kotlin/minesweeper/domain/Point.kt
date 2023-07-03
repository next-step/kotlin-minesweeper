package minesweeper.domain

data class Point(val x: Int, val y: Int): Comparable<Point> {
    override fun compareTo(other: Point): Int = if (y == other.y) x.compareTo(other.x) else y.compareTo(other.y)

    companion object {
        fun square(height: Int, width: Int): List<Point> = (0 until height).flatMap { y -> (0 until width).map { x -> Point(x, y) } }
    }
}
