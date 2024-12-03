package map

import cell.Element

class Grid(
    val points: List<MutableList<Point>>,
) {
    fun place(
        x: Int,
        y: Int,
        element: Element,
    ) {
        points[x][y] = Point(Pair(x, y), element)
    }
}
