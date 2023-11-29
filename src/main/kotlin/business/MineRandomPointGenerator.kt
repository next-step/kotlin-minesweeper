package business

class MineRandomPointGenerator : MinePointGenerator {
    override fun generate(boardInfo: BoardInfo): List<Point> =
        generateRandomNumber(boardInfo.height * boardInfo.width, boardInfo.mineCount).map {
            Point.indexToPoint(
                it, boardInfo.width
            )
        }

    private fun generateRandomNumber(total: Int, count: Int) = (START_INDEX until total).shuffled().take(count)

    companion object {
        private const val START_INDEX = 0
    }
}
