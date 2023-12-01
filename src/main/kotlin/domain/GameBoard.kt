package domain

import enum.CellStatus

class GameBoard(private val mineManager: MineManager) {
    private lateinit var board: Board

    val boardWidth: Int
        get() = if (::board.isInitialized) board.width else 0

    fun initializeBoard(height: Int, width: Int, mineCount: Int) {
        board = Board(height, width, mineManager)
        val minePositions = mineManager.minePlacementStrategy.placeMines(height, width, mineCount)
        board.initializeBoard(minePositions)
    }

    fun countMines(): Int = board.countMines()

    fun forEachCell(onEachCell: (Position, CellStatus) -> Unit) {
        board.forEachCell(onEachCell)
    }

    fun countMinesAround(position: Position): Int {
        return mineManager.mineCounter.countMinesAround(board, position)
    }
}
