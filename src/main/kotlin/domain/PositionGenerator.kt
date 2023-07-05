package domain

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
        return mutableListOf<Position>().apply {
            repeat(count) {
                val randomX = randomGenerator.random(xFrom, xUntil)
                val randomY = randomGenerator.random(yFrom, yUntil)
                add(Position(randomX, randomY))
            }
        }
    }
}

interface RandomGenerator {
    fun random(from: Int, until: Int): Int
}
