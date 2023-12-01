package domain

import enum.CellStatus

class GameBoard(private val mineManager: MineManager) {
    private lateinit var board: Board
    var height: Int = 0
    var width: Int = 0

    fun initializeBoard(height: Int, width: Int, mineCount: Int) {
        this.height = height
        this.width = width
        board = Board(mineManager)
        val minePositions = mineManager.minePlacementStrategy.placeMines(height, width, mineCount)
        board.initializeBoard(height, width, minePositions)
    }

    fun placeMines(mineCount: Int) {
        val minePositions = mineManager.minePlacementStrategy.placeMines(height, width, mineCount)
        minePositions.forEach { board.placeMineAt(it) }
    }

    fun countMines(): Int = board.countMines()

    fun forEachCell(onEachCell: (Position, CellStatus) -> Unit) {
        for (row in 0 until height) {
            processRow(row, onEachCell)
        }
    }

    fun countMinesAround(position: Position): Int {
        return mineManager.mineCounter.countMinesAround(board, position)
    }

    private fun processRow(row: Int, onEachCell: (Position, CellStatus) -> Unit) {
        for (column in 0 until width) {
            val position = Position(column, row)
            onEachCell(position, if (hasMineAt(position)) CellStatus.MINE else CellStatus.EMPTY)
        }
    }

    private fun hasMineAt(position: Position): Boolean = board.hasMineAt(position)
}
