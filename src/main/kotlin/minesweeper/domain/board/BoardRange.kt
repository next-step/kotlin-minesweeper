package minesweeper.domain.board

import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

class BoardRange(height: Int, width: Int) {

    private val heightRange: IntRange
    private val widthRange: IntRange
    init {
        require(height >= RANGE_MIN) { "높이는 1 이상이어야 합니다." }
        require(width >= RANGE_MIN) { "너비는 1 이상이어야 합니다." }

        heightRange = IntRange(RANGE_MIN, height)
        widthRange = IntRange(RANGE_MIN, width)
    }

    fun calculateArea(): Int = heightRange.max() * widthRange.max()

    fun maxHeight(): Int = heightRange.max()

    fun maxWidth(): Int = widthRange.max()

    fun createRandomPositions(minRandomPositionSize: Int = MIN_RANDOM_POSITION_SIZE): Positions {
        val positions = mutableSetOf<Position>()
        while (positions.size < minRandomPositionSize) {
            positions.add(Position(x = widthRange.random(), y = heightRange.random()))
        }
        return Positions(positions.toList())
    }

    companion object {
        private const val RANGE_MIN = 1
        private const val MIN_RANDOM_POSITION_SIZE = 1
    }
}
