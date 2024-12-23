package minesweeper

import minesweeper.model.Board
import minesweeper.model.MinesStrategy
import minesweeper.model.RandomMinesStrategy

class MinesWeeperService(
    height: Int,
    width: Int,
    mineCount: Int,
    private val minesStrategy: MinesStrategy = RandomMinesStrategy(),
) {
    private val board: Board = Board(height, width, mineCount)

    fun createCells(): Board {
        return board.createCells(minesStrategy)
    }

    fun openCells(row: Int, column: Int): Boolean {
        return board.openCells(row, column)
    }

    fun addMineAroundCounts() {
        board.addMineAroundCounts()
    }

    fun isFinishGame(): Boolean {
        return board.isFinishGame()
    }
}
