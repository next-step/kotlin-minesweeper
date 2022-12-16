package domain

import domain.block.Mine

class Field(
    val rows: List<Row>,
    mines: List<Coordinate>
) {
    init {
        mines.forEach { cell(it).updateBlock(Mine()) }
    }

    fun cell(coordinate: Coordinate): Cell {
        return rows[coordinate.y.value].cells[coordinate.x.value]
    }
}
