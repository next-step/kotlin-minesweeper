package minesweeper.domain

class MineLocations(
    private val mineLocations: List<MineLocationRow>,
    private val mapWidth: Int,
    private val mapHeight: Int,
) {
    operator fun get(index: Int) = mineLocations[index]

    fun getMapElement(point: Point): MapElement {
        if (isMine(point)) {
            return MineMapElement()
        }
        return NumberMapElement(getMineCount(point))
    }

    private fun getMineCount(point: Point): Int {
        return MOVE.count { (xMove, yMove) -> isMine(Point(point.x + xMove, point.y + yMove)) }
    }

    private fun isMine(point: Point): Boolean {
        if (point.x < 0 || point.y < 0 || point.x >= mapWidth || point.y >= mapHeight) {
            return false
        }
        return mineLocations[point.y].contains(point.x)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is MineLocations) {
            return false
        }

        return mineLocations == other.mineLocations && mapWidth == other.mapWidth && mapHeight == other.mapHeight
    }

    override fun hashCode(): Int {
        var result = mineLocations.hashCode()
        result = result * 31 + mapWidth
        result = result * 31 + mapHeight
        return result
    }

    companion object {
        private val MOVE = listOf(
            Pair(-1, -1),
            Pair(-1, 0),
            Pair(-1, 1),
            Pair(0, -1),
            Pair(0, 1),
            Pair(1, -1),
            Pair(1, 0),
            Pair(1, 1),
        )
    }
}

data class MineLocationRow(private val row: List<Int>) : Iterable<Int> by row

data class Point(val x: Int, val y: Int)
