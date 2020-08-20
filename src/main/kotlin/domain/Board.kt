package domain

import kotlin.math.max
import kotlin.math.min

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
        val mineLocations = createMines()
        for (i in 0 until mineCount) {
            setMine(mineLocations[i].first, mineLocations[i].second)
        }
    }

    private fun createMines() = (0 until height).flatMap { x ->
        (0 until width).map { y -> Pair(x, y) }
    }.shuffled().take(mineCount)

    private fun setMine(x: Int, y: Int) {
        if (boardInfo[x][y] > SYMBOL_MINE) {
            boardInfo[x][y] = SYMBOL_MINE
            notifySetMine(x, y)
        }
    }

    private fun notifySetMine(x: Int, y: Int) {
        for (location in findNotifyRange(x, y)) {
            increaseMineCount(location.first, location.second)
        }
    }

    private fun increaseMineCount(x: Int, y: Int) {
        if (boardInfo[x][y] > SYMBOL_MINE) {
            boardInfo[x][y] += 1
        }
    }

    private fun findNotifyRange(x: Int, y: Int): List<Pair<Int, Int>> {
        val rangeX = max(0, x - NOTIFY_RANGE)..min(x + NOTIFY_RANGE, height - 1)
        val rangeY = max(0, y - NOTIFY_RANGE)..min(y + NOTIFY_RANGE, width - 1)
        return rangeX.flatMap { i -> rangeY.map { j -> Pair(i, j) } }
    }

    override fun toString() = boardInfo.joinToString("") {
        it.joinToString(" ").replace(SYMBOL_MINE.toString(), SYMBOL_MINE_CHAR) + "\n"
    }

    companion object {
        private const val SYMBOL_MINE = -1
        private const val SYMBOL_MINE_CHAR = "X"
        private const val SYMBOL_EMPTY = 0
        private const val NOTIFY_RANGE = 1

        fun isValidMineCount(width: Int, height: Int, mineCount: Int) = mineCount in 0..(width * height)

        operator fun invoke(width: Int, height: Int, mineCount: Int): Board? {
            if (isValidMineCount(width, height, mineCount)) {
                return Board(width, height, mineCount)
            }
            return null
        }
    }
}
