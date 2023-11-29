package business

class Board(cells: List<List<Cell>>) {
    private val _cells: List<List<Cell>> = cells
    fun isMine(point: Point): Boolean = _cells[point.x][point.y].isMine()
    fun countMines(point: Point): Int = point.aroundPoints()
        .filter { isValidPoint(it) }
        .count { isMine(it) }

    private fun isValidPoint(it: Point): Boolean = it.x in _cells.indices && it.y in _cells[FIRST_INDEX].indices
    fun open(point: Point): GameStatus {
        if (isMine(point)) return GameStatus.GAME_OVER
        _cells[point.x][point.y].open()
        if (isSafePoint(point)) point.aroundPoints()
            .filter { isValidPoint(it) }
            .filter { !isOpen(it) }
            .forEach { open(it) }
        return GameStatus.CONTINUE
    }

    private fun isSafePoint(point: Point) = countMines(point) == SAFE_MINE_COUNT

    fun isOpen(point: Point): Boolean = _cells[point.x][point.y].isOpen()

    companion object {
        const val FIRST_INDEX = 0
        const val SAFE_MINE_COUNT = 0
    }
}
