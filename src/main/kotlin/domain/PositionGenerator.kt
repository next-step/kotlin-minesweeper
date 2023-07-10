package domain

import kotlin.math.abs

interface PositionGenerator {
    fun generate(count: Int): List<Position>
}

class RandomPositionGenerator(
    private val randomGenerator: RandomGenerator,
    private val xFrom: Int,
    private val xUntil: Int,
    private val yFrom: Int,
    private val yUntil: Int
) : PositionGenerator {
    override fun generate(count: Int): List<Position> {
        require(xLength() * yLength() >= count)

        return mutableSetOf<Position>().apply {
            while (size < count) {
                add(randomPosition())
            }
        }.toList()
    }

    private fun xLength() = abs(xFrom - xUntil) + 1

    private fun yLength() = abs(yFrom - yUntil) + 1

    private fun randomPosition(): Position = Position(randomX(), randomY())

    private fun randomX() = randomGenerator.random(xFrom, xUntil)

    private fun randomY() = randomGenerator.random(yFrom, yUntil)
}
