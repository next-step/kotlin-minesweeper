package domain

import exception.IllegalPositionException

data class Board(private val rows: List<Row>) : List<Row> by rows {
    constructor(height: Int, width: Int) : this((1..height).map { Row((1..width).map { Cell() }) })

    fun height(): Int = rows.size

    fun width(): Int = rows[0].size

    fun isAllOpen(): Boolean = flatten().all { it.isOpen() || it.isMine() }

    fun getCell(position: Position): Cell {
        val (i, j) = position.pair
        if (i > height() || j > width()) {
            throw IllegalPositionException()
        }
        return rows[i - 1][j - 1]
    }
}
