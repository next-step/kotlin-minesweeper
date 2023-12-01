package business

class Board(cells: Cells) {
    private val _cells: Cells = cells
    val cells: Cells
        get() = _cells.copy()
    fun isMine(point: Point): Boolean = _cells.isMine(point)
    fun open(point: Point): GameStatus {
        if (isMine(point)) {
            return GameStatus.GAME_OVER
        }
        _cells.open(point)
        if (_cells.isAllOpen()) {
            return GameStatus.WIN
        }
        return GameStatus.CONTINUE
    }

    companion object {
        const val SAFE_MINE_COUNT = 0

        fun of(boardInfo: BoardInfo, minePointGenerator: MinePointGenerator): Board =
            Board(Cells.of(boardInfo, minePointGenerator.generate(boardInfo)))
    }
}
