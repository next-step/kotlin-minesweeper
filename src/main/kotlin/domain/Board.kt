package domain

import exception.IllegalPositionException

data class Board(private val rows: List<Row>) : List<Row> by rows {
    constructor(height: Int, width: Int) : this(
        (1..height).map {
            Row((1..width).map { Cell() })
        }
    )

    fun height(): Int = rows.size

    fun width(): Int = rows[FIRST_ROW].size

    fun isAllOpen(): Boolean = flatten().all { it.isOpen() || it.isMine() }

    fun getCell(position: Position): Cell {
        val (rowIndex, columnIndex) = position
        if (rowIndex > height() || columnIndex > width()) {
            throw IllegalPositionException()
        }
        return rows[rowIndex - OFFSET][columnIndex - OFFSET]
    }

    companion object {
        private const val FIRST_ROW = 0
        private const val OFFSET = 1
    }
}
