package util

import kotlin.random.Random

object RandomGenerator {
    fun generateRandomLocation(rowLimit: Int, columnLimit: Int): Pair<Int, Int> {

        val randomRow = Random.nextInt(rowLimit)
        val randomColumn = Random.nextInt(columnLimit)

        return Pair(randomRow, randomColumn)
    }
}
