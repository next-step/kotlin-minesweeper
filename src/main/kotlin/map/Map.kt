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
        grid.place(rowIndex = rowIndex, columnIndex = columnsIndex, element = Mine.ready())
    }

    fun updateMineCountByCell(): Map {
        val updatedRows = Rows(rows = grid.points.rows
            .map { columns ->
                Columns(columns.columns.map { point: Point ->
                    // TODO 주위의 지뢰 개수를 세는 로직을 넣는다.
                    val adjacentMineCount = '1'
                    point.update(adjacentMineCount)
                }.toMutableList())
            })

        return Map(grid = Grid(updatedRows))
    }

    companion object {
        fun create(
            height: Height,
            width: Width,
            element: Element = Cell.ready(),
        ): Map = Map(grid = Grid(points = Rows.ready(height = height, width = width, element = element)))
    }
}
