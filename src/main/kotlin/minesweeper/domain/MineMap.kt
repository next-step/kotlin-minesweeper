package minesweeper.domain

class MineMap( // TODO 불변 객체로 변경해보자
    private val _values: MutableMap<Position, Cell> = mutableMapOf()
) {
    val values: Map<Position, Cell>
        get() = _values
    val size: Int
        get() = _values.keys.size

    fun plantCell(position: Position, cellState: CellState) {
        _values[position] = Cell(position, cellState)
    }

    fun getCell(position: Position): Cell {
        return _values[position] ?: throw IllegalArgumentException("해당 위치에 셀이 없습니다")
    }
}
