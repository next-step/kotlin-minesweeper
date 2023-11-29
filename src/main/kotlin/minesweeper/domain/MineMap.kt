package minesweeper.domain

class MineMap(
    private val _values: MutableMap<Position, Cell> = mutableMapOf()
) {
    val values: Map<Position, Cell>
        get() = _values
    val size: Int
        get() = _values.keys.size

    fun plantCell(position: Position, cell: Cell) {
        _values[position] = cell
    }

    fun getCell(position: Position): Cell {
        return _values[position] ?: throw IllegalArgumentException("해당 위치에 셀이 없습니다")
    }
}
