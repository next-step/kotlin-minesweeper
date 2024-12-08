package map

import cell.Cell
import cell.Element
import map.move.Direction
import map.move.Position
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
                    val adjacentMineCount = countAdjacentMines(p = point, rowSize = grid.points.rowSize, columnSize = grid.points.columnSize)
                    point.update(adjacentMineCount.toString())
                }.toMutableList())
            })

        return Map(grid = Grid(updatedRows))
    }

    private fun countAdjacentMines(
        p: Point,
        rowSize: Int,
        columnSize: Int,
    ): Int {
        val position = Position(row = p.rowIndex, column = p.columnIndex)
        return Direction.entries.count { direction ->
            val head = position.move(direction = direction, rowSize = rowSize, columnSize = columnSize)
            grid.isMine(position = head)
        }
    }

    companion object {
        fun create(
            height: Height,
            width: Width,
            element: Element = Cell.ready(),
        ): Map = Map(grid = Grid(points = Rows.ready(height = height, width = width, element = element)))
    }
}
