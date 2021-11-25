package domain

import exception.IllegalPositionException

data class Board(private val rows: List<Row>) : List<Row> by rows {
    constructor(height: Int, width: Int) : this((1..height).map { Row((1..width).map { Cell() }) })

    fun height(): Int = rows.size

    fun width(): Int = rows[0].size

    fun isAllOpen(): Boolean = flatten().all { it.isOpen() || it.isMine() }

    fun getCell(position: Position): Cell {
        val (rowIndex, columnIndex) = position.pair
        if (rowIndex > height() || columnIndex > width()) {
            throw IllegalPositionException()
        }
        return rows[rowIndex - 1][columnIndex - 1]
    }
}
