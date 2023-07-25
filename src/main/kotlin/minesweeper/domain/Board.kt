package minesweeper.domain

import minesweeper.domain.field.Mark
import minesweeper.domain.field.Position
import minesweeper.domain.generator.PositionGenerator
import minesweeper.domain.generator.RandomPositionGenerator

class Board private constructor(val markMap: Map<Position, Mark>) {
    companion object {
        fun create(boardMeta: BoardMeta, positionGenerator: PositionGenerator = RandomPositionGenerator()): Board {
            val minePositions = positionGenerator.get(boardMeta)
            val baseBoard = generateAllPositions(boardMeta.boardSize)
                .associateWith { Mark.SAFE }
                .toMutableMap()
            baseBoard.putAll(minePositions.associateWith { Mark.MINE })
            return Board(baseBoard.toMap())
        }

        private fun generateAllPositions(boardSize: BoardSize): List<Position> {
            return (0 until boardSize.width).flatMap { w ->
                (0 until boardSize.height).map { h ->
                    Position(w, h)
                }
            }
        }
    }
}
