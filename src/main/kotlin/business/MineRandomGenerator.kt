package business

class MineRandomGenerator : MineGenerator {
    override fun generate(height: Int, width: Int, count: Int): Mines {
        val total = height * width
        return generateRandomNumber(total, count)
            .map { Point.indexToPoint(it, width) }
            .map { Mine(it) }
            .let { Mines(it) }
    }

    private fun generateRandomNumber(total: Int, count: Int) = (START_INDEX until total).shuffled().take(count)

    companion object {
        private const val START_INDEX = 0
    }
}
