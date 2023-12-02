package minesweeper

data class Point(val row: Int, val col: Int) {
    fun getAdjacentPoint(mapSize: MapSize): List<Point> {
        val mapRow = mapSize.row.count
        val mapCol = mapSize.column.count
        return buildList {
            for (dir in Direction.values()) {
                val nearPoint = Point(row + dir.row, col + dir.col)
                if (nearPoint.isOutOfBound(mapRow, mapCol)) continue
                add(nearPoint)
            }
        }
    }

    private fun Point.isOutOfBound(mapRow: Int, mapCol: Int): Boolean =
        this.row < 1 || this.col < 1 || this.row > mapRow || this.col > mapCol

    enum class Direction(val row: Int, val col: Int) {
        NORTH(-1, 0),
        NORTHEAST(-1, 1),
        EAST(0, 1),
        SOUTHEAST(1, 1),
        SOUTH(1, 0),
        SOUTHWEST(1, -1),
        WEST(0, -1),
        NORTHWEST(-1, -1)
    }
}
