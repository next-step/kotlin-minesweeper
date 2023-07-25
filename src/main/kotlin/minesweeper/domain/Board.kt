package minesweeper.domain

import minesweeper.domain.field.Mark
import minesweeper.domain.field.Position

class Board(val board: Map<Position, Mark>) {

    fun plantMines(minePositions: Set<Position>): Board {
        val withMineBoard = board.toMutableMap()
        minePositions.forEach { position ->
            withMineBoard[position] = Mark.MINE
        }
        return Board(withMineBoard.toMap())
    }
}
