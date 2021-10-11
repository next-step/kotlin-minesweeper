package model

import model.cell.Cell
import model.position.Position
import model.row.Row

data class Board(
    private val rows: List<Row>
) {
    fun open(position: Position) {
        val (width, height) = position.indexPair()
        val cell: Cell = rows[height].get(width)

        cell.open()
    }
}