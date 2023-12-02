package domain

import enum.CellStatus

class GameBoard(private val mineManager: MineManager) {
    private lateinit var board: Board
    var width: Int = 0
    private var height: Int = 0

    fun setupBoardAndPlaceMines(height: Int, width: Int, mineCount: Int) {
        this.height = height
        this.width = width
        board = Board(height, width, mineManager)
        val minePositions = mineManager.minePlacementStrategy.placeMines(height, width, mineCount)
        board.placeMines(minePositions)
    }

    fun countMines(): Int = board.countMines()

    fun processEachCell(onEachCell: (Position, CellStatus) -> Unit) {
        board.forEachCell(onEachCell)
    }

    fun countMinesAround(position: Position): Int {
        return mineManager.mineCounter.countMinesAround(board, position, height, width)
    }
}
