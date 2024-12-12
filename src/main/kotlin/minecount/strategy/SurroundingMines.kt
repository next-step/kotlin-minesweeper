package minecount.strategy

import map.Index
import map.Rows
import map.move.Direction
import map.move.Position

class SurroundingMines(
    override val points: Rows,
) : MineCountStrategy {
    override fun calculate(
        rowIndex: Index?,
        columnIndex: Index?,
    ): Int {
        val position = Position(row = rowIndex, column = columnIndex)
        return Direction.entries.count { isMineInDirection(it, position) }
    }

    private fun isMineInDirection(
        direction: Direction,
        position: Position,
    ): Boolean {
        val row = position.row ?: return false
        val column = position.column ?: return false

        val head = position.move(direction = direction, rowSize = row.maxSize, columnSize = column.maxSize)

        if (head.row == null || head.column == null) {
            return false
        }

        return points.columns[head.row.value]
            .points[head.column.value]
            .isMine()
    }
}
