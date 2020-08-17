package game

class Board private constructor(
    private val width: Int,
    private val height: Int,
    private val mineCount: Int = 0
) {
    private val boardInfo = MutableList(height) { MutableList(width) { SYMBOL_EMPTY } }

    init {
        initBoard()
    }

    private fun initBoard() {
        var count = 0
        val location = createRandomLocation()
        while (count < mineCount) {
            count += setMine(location[count].first, location[count].second)
        }
    }

    private fun createRandomLocation() = (0 until height).flatMap { x ->
        (0 until width).map { y -> Pair(x, y) }
    }.shuffled().take(mineCount)

    private fun setMine(x: Int, y: Int): Int {
        if (boardInfo[x][y] == SYMBOL_EMPTY) {
            boardInfo[x][y] = SYMBOL_MINE
            return 1
        }
        return 0
    }

    override fun toString() = boardInfo.joinToString("") { it.joinToString(" ") + "\n" }

    companion object {
        private const val SYMBOL_EMPTY = "O"
        private const val SYMBOL_MINE = "X"

        fun isValidMineCount(width: Int, height: Int, mineCount: Int) = mineCount in 0..(width * height)

        operator fun invoke(width: Int, height: Int, mineCount: Int): Board? {
            if (isValidMineCount(width, height, mineCount)) {
                return Board(width, height, mineCount)
            }
            return null
        }
    }
}
