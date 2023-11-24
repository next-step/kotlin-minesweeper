package domain

import enum.CellStatus

class GameBoard(val height: Int, val width: Int) {
    private val board = Board(height, width)

    fun placeMines(mineCount: Int) {
        val allPositions = (0 until height).flatMap { y -> (0 until width).map { x -> Position(x, y) } }
        allPositions.shuffled().take(mineCount).forEach { board.placeMineAt(it) }
    }

    fun countMines(): Int = board.countMines()

    fun forEachCell(action: (Position, CellStatus) -> Unit) {
        for (y in 0 until height) {
            for (x in 0 until width) {
                val position = Position(x, y)
                action(position, if (hasMineAt(position)) CellStatus.MINE else CellStatus.EMPTY)
            }
        }
    }

    private fun hasMineAt(position: Position): Boolean = board.hasMineAt(position)
}
