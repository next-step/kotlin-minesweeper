package util

import domain.Location
import kotlin.random.Random

class RandomLocationGenerator(
    private val rowLimit: Int,
    private val columnLimit: Int
) : LocationGenerator {
    override fun generate(): Location {
        val randomRow = Random.nextInt(rowLimit)
        val randomColumn = Random.nextInt(columnLimit)

        return Location(randomRow, randomColumn)
    }
}
