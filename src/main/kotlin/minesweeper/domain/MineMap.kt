package minesweeper.domain

class MineMap(
    values: Map<Position, Cell>
) {
    private val _values = values.toMutableMap()
    val values: Map<Position, Cell>
        get() = _values.toMap()

    val size: Int
        get() = _values.keys.size

    fun isEmptyCellClicked(position: Position): Boolean {
        val cell = getCell(position)
        return if (cell is Mine) {
            false
        } else {
            openAroundCells(position)
            true
        }
    }

    fun getCell(position: Position): Cell {
        return _values[position] ?: throw IllegalArgumentException("해당 위치에 셀이 없습니다")
    }

    private fun openAroundCells(position: Position) {
        val aroundPositions = position.aroundPositions()
        (aroundPositions + position).forEach(::openCell)
    }

    private fun openCell(position: Position) {
        val cell = runCatching { getCell(position) }.getOrNull() ?: return
        val newCell = cell.open()
        _values[position] = newCell
    }
}
