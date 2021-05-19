package model.board

import model.MineScope
import model.Position

class Board(rows: List<Row>) {
    val rows = rows.toList()

    val height: Int
        get() = rows.size

    val width: Int
        get() = rows.first().width

    init {
        require(rows.isNotEmpty()) { "빈 rows 로는 Board 를 만들 수 없습니다!" }
        require(rows.all { it.width == rows.first().width }) { "모든 row 의 길이가 같아야 합니다!" }
    }

    constructor(vararg rows: Row) : this(rows.toList())

    fun getCell(position: Position): Cell {
        return rows[position.heightIndex].getCell(position.widthIndex)
    }

    fun uncover(position: Position) {
        rows[position.heightIndex].uncover(position.widthIndex, MineScope(position, height, width).countMine(rows))
    }

    fun uncoverAll() {
        (0 until height).forEach { heightIndex ->
            (0 until width).forEach { widthIndex ->
                uncover(Position.get(heightIndex, widthIndex))
            }
        }
    }
}
