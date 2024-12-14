package map

import cell.Cell
import cell.Element
import map.move.Direction
import map.move.Position
import mine.Mine
import mine.MinePoints
import minecount.strategy.SurroundingMines
import open.result.OpenResult
import open.result.OpenResult.InvalidPosition
import open.result.OpenResult.MineExploded
import open.result.OpenResult.Success

class Map(
    var grid: Grid,
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

    fun openAdjacent(position: Position): Map {
        val row = position.row ?: return this
        val column = position.column ?: return this
        val adjacentPositions =
            Direction.entries
                .map { position.move(direction = it, rowSize = row.maxSize, columnSize = column.maxSize) }
                .filter { isOpenPosition(it) }

        adjacentPositions.forEach {
            grid = grid.open(
                rowIndex = it.row ?: return@forEach,
                columnIndex = it.column ?: return@forEach,
            ) ?: return@forEach

            openAdjacent(it)
        }

        return this
    }

    private fun isOpenPosition(position: Position): Boolean {
        val row = position.row ?: return false
        val column = position.column ?: return false

        // TODO : Rows에게 메시지를 던진다
        return grid.rows
            .getColumn(row)
            ?.points
            ?.get(column.value)
            ?.isOpenAdjacentCell()
            ?: return false
    }

    companion object {
        fun create(
            height: Height,
            width: Width,
            element: Element = Cell.ready(),
        ): Map {
            val rows = Rows.ready(height = height, width = width, element = element)
            return Map(grid = Grid(rows = rows, mineCountStrategy = SurroundingMines(points = rows)))
        }
    }
}
