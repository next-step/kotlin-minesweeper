package map

import cell.Cell
import cell.Element
import mine.Mine
import mine.MinePoints

class Map(
    val grid: Grid,
) {
    fun placeMine(minePoints: MinePoints) {
        minePoints.points.forEach { placeMineAtPoint(it) }
    }

    private fun placeMineAtPoint(point: Point) {
        val (rowIndex, columnsIndex) = point.point
        grid.place(rowIndex = rowIndex, columnIndex = columnsIndex, element = Mine)
    }

    companion object {
        fun create(
            height: Height,
            width: Width,
            element: Element = Cell,
        ): Map = Map(grid = Grid(points = Rows.ready(height = height, width = width, element = element)))
    }
}
