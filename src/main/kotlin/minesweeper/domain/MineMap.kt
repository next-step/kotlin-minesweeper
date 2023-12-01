package minesweeper.domain

class MineMap(
    private val values: Map<Position, Cell>
) {
    val size: Int
        get() = values.keys.size

    fun getCell(position: Position): Cell {
        return values[position] ?: throw IllegalArgumentException("해당 위치에 셀이 없습니다")
    }
}