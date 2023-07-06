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
}
