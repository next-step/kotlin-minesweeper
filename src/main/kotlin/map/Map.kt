package map

import cell.Cell

class Map(
    val grid: Grid,
) {
    companion object {
        fun create(
            height: Height,
            width: Width,
        ): Map {
            val points =
                List(size = height.size) { row ->
                    MutableList(size = width.size) { col ->
                        Point(Pair(row, col), Cell)
                    }
                }

            return Map(grid = Grid(points = points))
        }
    }
}
