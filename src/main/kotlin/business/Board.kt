package business

class Board(cells: List<List<Cell>>) {
    private val _cells: List<List<Cell>> = cells
    fun isMine(point: Point): Boolean = _cells[point.x][point.y].isMine()
    fun countMines(point: Point): Int = point.aroundPoints()
        .filter { isValidPoint(it) }
        .count { isMine(it) }

    private fun isValidPoint(it: Point): Boolean = it.x in _cells.indices && it.y in _cells[FIRST_INDEX].indices

    companion object {
        const val FIRST_INDEX = 0
    }
}
