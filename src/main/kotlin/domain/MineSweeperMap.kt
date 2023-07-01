package domain

import kotlin.random.Random
import kotlin.random.nextInt

class MineSweeperMap(private val property: Property) {
    val value: Array<CharArray>

    init {
        value = initGameMap()
        setMine()
    }

    private fun initGameMap(): Array<CharArray> {
        return with(property) {
            Array(height.value) { CharArray(width.value) { NON_MINE_CHAR } }
        }
    }

    private fun setMine() {
        with(property) {
            val gameMapRange = 0 until height.value * width.value
            val numberSet = mutableSetOf<Pair<Int, Int>>()

            while (numberSet.size < mineCount.value) {
                val randomNumber = Random.nextInt(gameMapRange)
                val row = randomNumber / width.value
                val column = randomNumber % width.value
                numberSet.add(row to column)
            }

            numberSet.forEach { (row, height) -> value[row][height] = MINE_CHAR }
        }
    }

    companion object {
        const val MINE_CHAR = '*'
        const val NON_MINE_CHAR = 'C'
    }

    data class Property(
        val height: PositiveNumber,
        val width: PositiveNumber,
        val mineCount: MineCountNumber,
    )
}
