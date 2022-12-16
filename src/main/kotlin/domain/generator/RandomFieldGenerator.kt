package domain.generator

import domain.Coordinate
import domain.Field

class RandomFieldGenerator : FieldGenerator {
    override fun generate(height: Int, width: Int, mineCount: Int): Field {
        val mineCoordinates = createMineCoordinates(height, width, mineCount)

        return Field.create(height, width, mineCoordinates)
    }

    private fun createMineCoordinates(height: Int, width: Int, mineCount: Int): List<Coordinate> =
        (MIN_VALUE until height).flatMap { y ->
            (MIN_VALUE until width).map { x ->
                Coordinate(y, x)
            }
        }.shuffled().subList(0, mineCount)

    companion object {
        private const val MIN_VALUE = 0
    }
}
