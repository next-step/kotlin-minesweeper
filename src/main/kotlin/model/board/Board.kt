package model.board

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

    fun getCell(position: Position): Cell {
        return rows[position.heightIndex].getCell(position.widthIndex)
    }

    fun uncover(position: Position) {
        val heightRange = (position.heightIndex - 1).coerceAtLeast(0)..(position.heightIndex + 1).coerceAtMost(height - 1)
        val widthRange = (position.widthIndex - 1).coerceAtLeast(0)..(position.widthIndex + 1).coerceAtMost(width - 1)

        val mineCount = heightRange.fold(0) { sum, heightIndex -> sum + widthRange.fold(0) { sum, widthIndex -> sum + if (rows[heightIndex].isMine(widthIndex)) 1 else 0 } }

        rows[position.heightIndex].uncover(position.widthIndex, mineCount)
    }
}
