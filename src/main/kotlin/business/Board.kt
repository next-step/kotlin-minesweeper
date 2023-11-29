package business

class Board(cells: Cells) {
    private val _cells: Cells = cells
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

    fun isOpen(point: Point): Boolean = _cells.isOpen(point)
    fun countMines(point: Point): Any = _cells.countMines(point)
    fun executeWithOpenStatusAndMineCount(action: (Boolean, Int) -> Unit, rowAction: () -> Unit) {
        _cells.executeWithOpenStatusAndMineCount(action, rowAction)
    }

    fun executeWithMineStatusAndCount(action: (Boolean, Int) -> Unit, rowAction: () -> Unit) {
        _cells.executeWithMineStatusAndCount(action, rowAction)
    }

    companion object {
        const val SAFE_MINE_COUNT = 0

        fun of(boardInfo: BoardInfo, minePointGenerator: MinePointGenerator): Board =
            Board(Cells.of(boardInfo, minePointGenerator.generate(boardInfo)))
    }
}
