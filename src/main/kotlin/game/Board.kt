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
        var count = mineCount
        while (count > 0) {
            val x = (0 until width).random()
            val y = (0 until height).random()
            count -= setMine(x, y)
        }
    }

    private fun setMine(x: Int, y: Int): Int {
        val location = boardInfo[x][y]
        if (location == SYMBOL_EMPTY) {
            boardInfo[x][y] = SYMBOL_MINE
            return 1
        }
        return 0
    }

    override fun toString() = boardInfo.joinToString("") { it.joinToString("") + "\n" }

    companion object {
        private const val SYMBOL_EMPTY = "ㅁ"
        private const val SYMBOL_MINE = "X"

        operator fun invoke(width: Int, height: Int, mineCount: Int): Board? {
            if (mineCount in 0..(width * height)) {
                return Board(width, height, mineCount)
            }
            return null
        }
    }
}
