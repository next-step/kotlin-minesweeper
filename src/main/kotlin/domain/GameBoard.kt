package domain

import enum.CellStatus

class GameBoard(private val mineManager: MineManager) {
    private lateinit var board: Board
    var width: Int = 0
    private var height: Int = 0
    var isGameOver = false

    fun setupBoardAndPlaceMines(height: Int, width: Int, mineCount: Int) {
        this.height = height
        this.width = width
        board = Board(height, width, mineManager)
        board.closeAllCells()
        board.placeMines(mineCount)
    }

    fun countMinesAround(position: Position): Int {
        return board.countMinesAround(position)
    }

    fun processEachCell(onEachCell: (Position, CellStatus) -> Unit) {
        board.processEachCell(onEachCell)
    }

    fun openCell(position: Position): Boolean {
        if (isGameOver) return false

        val cellOpened = board.openCell(position)
        if (cellOpened && board.hasMineAt(position)) {
            isGameOver = true
            return true
        }

        return false
    }
}
