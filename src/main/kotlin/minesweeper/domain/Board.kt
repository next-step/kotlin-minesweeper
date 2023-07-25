package minesweeper.domain

import minesweeper.domain.field.Mark
import minesweeper.domain.field.Position

class Board private constructor(val markMap: Map<Position, Mark>) {
    companion object {
        fun create(width: Int, height: Int, minePositions: Set<Position>): Board {
            val board = generateAllPositions(width, height)
                .associateWith { Mark.SAFE }
                .toMutableMap()
            board.putAll(minePositions.associateWith { Mark.MINE })
            return Board(board.toMap())
        }

        private fun generateAllPositions(width: Int, height: Int): List<Position> {
            return (0 until width).flatMap { i ->
                (0 until height).map { j ->
                    Position(i, j)
                }
            }
        }
    }
}
