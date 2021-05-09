package domain

import domain.position.Position
import domain.square.Square

class Squares(
    private val rows: List<Row>
) {
    fun get(position: Position): Square {
        return rows[position.row][position.col]
    }

    fun getRow(rowNum: Int): Row {
        return rows[rowNum]
    }

    fun hasAllOpened(): Boolean {
        return rows.all { row ->
            row.filter { !it.isMine }.all { it.isOpen }
        }
    }
}
