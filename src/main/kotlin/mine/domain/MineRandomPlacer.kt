package mine.domain

import mine.domain.Mine.Companion.MINE_SYMBOL
import mine.domain.Mine.Companion.NORMAL_SYMBOL

class MineRandomPlacer {
    fun placeMines(
        height: Int,
        width: Int,
        mineCount: Int,
    ): Array<Array<String>> {
        val board = Array(height) { Array(width) { NORMAL_SYMBOL } }
        var placedMines = PLACE_MINE_DEFAULT

        while (placedMines < mineCount) {
            val randomRow = getRandomValue(height)
            val randomCol = getRandomValue(height)
            if (board[randomRow][randomCol] == MINE_SYMBOL) continue
            board[randomRow][randomCol] = MINE_SYMBOL
            placedMines++
        }

        return board
    }

    private fun getRandomValue(target: Int) = (RANDOM_MINE_VALUE until target).random()

    companion object {
        private const val PLACE_MINE_DEFAULT = 0
        private const val RANDOM_MINE_VALUE = 0
    }
}
