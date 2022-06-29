import domain.Point

class MineSweeperBoardGenerator(private val gameSettingInfo: GameSettingInfo) : BoardGenerator {

    override fun create(): Map<Point, Square> {
        val height = gameSettingInfo.height
        val width = gameSettingInfo.width

        val shuffledSquares = createSquares().toMutableList()

        val board: MutableMap<Point, Square> = mutableMapOf()

        for (i in FIRST_INDEX until height) {
            for (j in FIRST_INDEX until width) {
                val point = Point(i, j)
                board[point] = shuffledSquares.removeFirst()
            }
        }

        return board.toMap()
    }

    private fun createSquares(): List<Square> {
        val squares: MutableList<Square> = mutableListOf()
        repeat(gameSettingInfo.mineCount) {
            squares.add(Mine())
        }

        val nonMineCount = gameSettingInfo.height * gameSettingInfo.width - gameSettingInfo.mineCount
        repeat(nonMineCount) {
            squares.add(NonMine())
        }

        return squares.shuffled()
    }

    companion object {
        private const val FIRST_INDEX = 0
    }
}
