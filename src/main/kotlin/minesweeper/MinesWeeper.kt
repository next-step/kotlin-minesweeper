package minesweeper

import minesweeper.model.Board
import minesweeper.model.Cell

/**
 * @author 이상준
 */
class MinesWeeper(
    height: Int,
    width: Int,
    mineCount: Int,
) {
    private val board: Board = Board(height, width, mineCount)

    fun createCells(): List<Cell> {
        return board.createCells()
    }
}
