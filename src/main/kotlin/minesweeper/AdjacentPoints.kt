package minesweeper

class AdjacentPoints private constructor(val points: List<Point>) {
    companion object {
        fun create(center: Point, mapRow: Int, mapCol: Int) =
            AdjacentPoints(
                buildList {
                    for (dir in Direction.values()) {
                        val nearPoint = Point(center.row + dir.row, center.col + dir.col)
                        if (nearPoint.isOutOfBound(mapRow, mapCol)) continue
                        add(nearPoint)
                    }
                }
            )

        private fun Point.isOutOfBound(mapRow: Int, mapCol: Int): Boolean =
            this.row < 0 || this.col < 0 || this.row >= mapRow || this.col >= mapCol
    }

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
