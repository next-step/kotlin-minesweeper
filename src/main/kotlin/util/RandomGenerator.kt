package util

import domain.Location
import kotlin.random.Random

object RandomGenerator {
    fun generateRandomLocation(rowLimit: Int, columnLimit: Int): Location {

        val randomRow = Random.nextInt(rowLimit)
        val randomColumn = Random.nextInt(columnLimit)

        return Location(randomRow, randomColumn)
    }
}
