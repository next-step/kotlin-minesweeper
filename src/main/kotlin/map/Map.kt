package map

import cell.Cell
import mine.Mine

class Map(
    val grid: Grid,
) {
    fun placeMine(point: Point) {
        val (x, y) = point.point
        grid.place(x = x, y = y, element = Mine)
    }

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
