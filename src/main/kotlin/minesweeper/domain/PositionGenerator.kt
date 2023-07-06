package minesweeper.domain

object PositionGenerator {
    fun generatePositionsRandomly(width: PositiveInt, height: PositiveInt, count: PositiveInt): List<Position> {
        return (0 until width.value * height.value)
            .shuffled()
            .take(count.value)
            .map { index ->
                Position(index % width.value, index / width.value)
            }
    }

    fun generatePositionsExcept(width: PositiveInt, height: PositiveInt, excepts: List<Position>): List<Position> {
        val allPositions = (0 until width.value * height.value)
            .map { index -> Position(index % width.value, index / width.value) }

        return allPositions - excepts.toSet()
    }
}
