package business

class MineRandomPointGenerator : MinePointGenerator {
    override fun generate(boardInfo: BoardInfo): Points =
        Points(generateRandomNumber(boardInfo.size, boardInfo.mineCount).map { changeToPoint(it, boardInfo.width) })

    private fun generateRandomNumber(total: Int, count: Int) = (START_INDEX until total).shuffled().take(count)

    private fun changeToPoint(it: Int, width: Int) = Point(getHeight(it, width), getWidth(it, width))

    private fun getWidth(it: Int, width: Int) = it % width

    private fun getHeight(it: Int, width: Int) = it / width

    companion object {
        private const val START_INDEX = 0
    }
}
