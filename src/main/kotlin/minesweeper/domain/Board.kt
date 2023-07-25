package minesweeper.domain

import minesweeper.domain.field.Mark
import minesweeper.domain.field.Position

class Board private constructor(val markMap: Map<Position, Mark>) {
    companion object {
        fun create(boardSize: BoardSize, minePositions: Set<Position>): Board {
            val board = generateAllPositions(boardSize)
                .associateWith { Mark.SAFE }
                .toMutableMap()
            board.putAll(minePositions.associateWith { Mark.MINE })
            return Board(board.toMap())
        }

        private fun generateAllPositions(boardSize: BoardSize): List<Position> {
            return (0 until boardSize.width).flatMap { i ->
                (0 until boardSize.height).map { j ->
                    Position(i, j)
                }
            }
        }
    }
}
