package map

import cell.Cell
import cell.Element
import map.move.Position
import mine.Mine
import mine.MinePoints
import minecount.strategy.SurroundingMines
import open.result.OpenResult
import open.result.OpenResult.InvalidPosition
import open.result.OpenResult.Success
import open.result.OpenResult.MineExploded

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

    fun updateMineCountByCell(): Map = Map(grid = grid.updateMineCountByCell())

    fun open(position: Position): OpenResult {
        return Success(
            Map(
                grid.open(
                    rowIndex = position.row ?: return InvalidPosition,
                    columnIndex = position.column ?: return InvalidPosition,
                ) ?: return MineExploded,
            ),
        )
    }

    companion object {
        fun create(
            height: Height,
            width: Width,
            element: Element = Cell.ready(),
        ): Map {
            val rows = Rows.ready(height = height, width = width, element = element)
            return Map(grid = Grid(points = rows, mineCountStrategy = SurroundingMines(points = rows)))
        }
    }
}
