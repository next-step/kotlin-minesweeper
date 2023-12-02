package minesweeper

data class Point(val row: Int, val col: Int) {
    fun getAdjacentPoints(mapSize: MapSize): List<Point> {
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
}
