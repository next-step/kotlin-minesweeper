package minesweeper.model

class MineSweeperGame(board: Board) {
    private var _board: Board = board
    val board: Board
        get() {
            return Board(_board.board)
        }

    private val boardMaker = MineBoardMaker()

    constructor(row: Int, col: Int) : this(board = Board(row, col))

    fun generateBoard(mineCount: Int) {
        _board = boardMaker.setMinePosition(_board, mineCount)
        _board = boardMaker.setMineCount(_board)
    }
}
