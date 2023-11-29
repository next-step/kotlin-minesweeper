package business

class Board(cells: Cells) {
    private val _cells: Cells = cells
    fun isMine(point: Point): Boolean = _cells.isMine(point)
    fun open(point: Point): GameStatus {
        return if (isMine(point)) GameStatus.GAME_OVER
        else {
            _cells.open(point)
            GameStatus.CONTINUE
        }
    }

    fun isOpen(point: Point): Boolean = _cells.isOpen(point)
    fun countMines(point: Point): Any = _cells.countMines(point)

    companion object {
        const val SAFE_MINE_COUNT = 0
    }
}
