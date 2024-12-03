package map

import cell.Cell

class Map(
    val points: List<List<Point>>,
) {
    companion object {
        fun create(
            height: Height,
            width: Width,
        ): Map =
            Map(
                points =
                    List(size = height.size) { row ->
                        List(size = width.size) { col ->
                            Point(Pair(row, col), Cell)
                        }
                    },
            )
    }
}
