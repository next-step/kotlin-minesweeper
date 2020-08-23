package minesweeper.model

class MineSweeperGame(board: Board) {
    private var _board: Board = board
    val board: Board
        get() {
            return Board(_board.board)
        }

    constructor(row: Int, col: Int) : this(board = Board(row, col))

    fun generateMine(mineCount: Int) {
        _board = _board.setMinePosition(mineCount)
        _board = _board.setNearbyMineCount()
    }
}
