package minesweeper.domain.position

import minesweeper.domain.board.BoardRange

@JvmInline
value class Positions(private val positions: List<Position>) : List<Position> by positions {

    companion object {
        private const val MIN_RANDOM_POSITION_SIZE = 1

        fun createRandomPositions(
            minRandomPositionSize: Int = MIN_RANDOM_POSITION_SIZE,
            boardRange: BoardRange,
        ): Positions {
            val positions = mutableSetOf<Position>()
            while (positions.size < minRandomPositionSize) {
                positions.add(Position(x = boardRange.randomWidth(), y = boardRange.randomHeight()))
            }
            return Positions(positions.toList())
        }
    }
}
