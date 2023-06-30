package domain

import kotlin.random.Random
import kotlin.random.nextInt

class MineSweeperMap(height: PositiveNumber, width: PositiveNumber, mineCount: MineCountNumber) {
    val value: Array<CharArray>

    init {
        value = initGameMap(height.value, width.value)
        setMine(height.value, width.value, mineCount.value)
    }

    private fun initGameMap(height: Int, width: Int): Array<CharArray> {
        return Array(height) { CharArray(width) { NON_MINE_CHAR } }
    }

    private fun setMine(height: Int, width: Int, mineCount: Int) {
        val gameMapRange = 0 until height * width
        val numberSet = mutableSetOf<Pair<Int, Int>>()

        while (numberSet.size < mineCount) {
            val randomNumber = Random.nextInt(gameMapRange)
            val row = randomNumber / width
            val column = randomNumber % width
            numberSet.add(row to column)
        }

        numberSet.forEach { (row, height) -> value[row][height] = MINE_CHAR }
    }

    companion object {
        const val MINE_CHAR = '*'
        const val NON_MINE_CHAR = 'C'
    }
}
