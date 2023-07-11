package minesweeper.domain

class MinePoints(private val points: Set<Point>) {
    fun contains(point: Point): Boolean = points.contains(point)

    fun countNeighborMines(point: Point): Int {
        return point.getAdjacentPoints().count { it in points }
    }
}
