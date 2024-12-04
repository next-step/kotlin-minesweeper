package map

import mine.Mine

class Map(
    val grid: Grid,
) {
    fun placeMine(minePoints: List<Point>) {
        minePoints.forEach { placeMineAtPoint(it) }
    }

    private fun placeMineAtPoint(point: Point) {
        val (rowIndex, columnsIndex) = point.point
        grid.place(rowIndex = rowIndex, columnIndex = columnsIndex, element = Mine)
    }

    companion object {
        fun create(
            height: Height,
            width: Width,
        ): Map = Map(grid = Grid(points = Rows.ready(height, width)))
    }
}
