package business

class MineRandomPointGenerator : MinePointGenerator {
    override fun generate(height: Int, width: Int, count: Int): List<Point> =
        generateRandomNumber(height * width, count).map { Point.indexToPoint(it, width) }

    private fun generateRandomNumber(total: Int, count: Int) = (START_INDEX until total).shuffled().take(count)

    companion object {
        private const val START_INDEX = 0
    }
}
