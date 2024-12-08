package map.move

import map.Index

class Position(
    val row: Index?,
    val column: Index?,
) {
    fun move(
        direction: Direction,
        rowSize: Int,
        columnSize: Int,
    ): Position {
        require(row != null && column != null) { "현재 위치가 존재하지 않습니다 " }
        return Position(
            row = Index.create(value = row.value + direction.row, maxSize = rowSize),
            column = Index.create(value = column.value + direction.column, maxSize = columnSize),
        )
    }
}
