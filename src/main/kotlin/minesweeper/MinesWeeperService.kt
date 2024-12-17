package minesweeper

import minesweeper.model.Board
import minesweeper.model.Cell


class MinesWeeperService(
    height: Int,
    width: Int,
    mineCount: Int,
) {
    private val board: Board = Board(height, width, mineCount)

    fun createCells(): List<Cell> {
        return board.createCells()
    }

    fun addMineAroundCounts() {
        board.addMineAroundCounts()
    }
}
