package map

import cell.Cell
import cell.Element
import map.move.Direction
import map.move.Position
import mine.Mine

data class Point(
    val point: Pair<Index?, Index?>,
    val element: Element = Cell.ready(),
) {
    private val rowIndex: Index?
        get() {
            return point.first
        }

    private val columnIndex: Index?
        get() {
            return point.second
        }

    fun updateMineCounts(rows: Rows): Point {
        val adjacentMineCount = countAdjacentMines(rows)
        return this.update(adjacentMineCount.toString())
    }

    private fun countAdjacentMines(rows: Rows): Int {
        if (rowIndex == null || columnIndex == null) {
            return 0
        }

        val position = Position(row = rowIndex, column = columnIndex)
        return Direction.entries.count { isMineInDirection(direction = it, position = position, rows = rows) }
    }

    private fun isMineInDirection(
        direction: Direction,
        position: Position,
        rows: Rows,
    ): Boolean {
        val head = position.move(direction = direction, rowSize = rows.rowSize, columnSize = rows.columnSize)

        if (head.row == null || head.column == null) {
            return false
        }

        return rows.rows
            .getOrNull(head.row.value)
            ?.columns
            ?.getOrNull(head.column.value)
            ?.isMine() ?: false
    }

    private fun update(element: String): Point =
        Point(
            point = this.point,
            element = this.element.updateValue(newValue = element),
        )

    fun isMine(): Boolean = element is Mine
}
