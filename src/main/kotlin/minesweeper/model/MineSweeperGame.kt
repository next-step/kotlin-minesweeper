package minesweeper.model

class MineSweeperGame(board: Board) {
    private var _board: Board = board
    val board: Board = _board.copy()

    constructor(row: Int, col: Int, mineCount: Int) : this(board = Board(row, col, mineCount))
}
