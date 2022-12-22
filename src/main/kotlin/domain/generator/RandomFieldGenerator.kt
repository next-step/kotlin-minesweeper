package domain.generator

import domain.Coordinate
import domain.Field
import domain.LocationOfMines

class RandomFieldGenerator : FieldGenerator {
    override fun generate(height: Int, width: Int, mineCount: Int): Field {
        val mineCoordinates = createMineCoordinates(height, width, mineCount)

        return Field.create(height, width, mineCoordinates)
    }

    private fun createMineCoordinates(height: Int, width: Int, mineCount: Int): LocationOfMines =
        LocationOfMines(
            (MIN_VALUE until height).flatMap { y ->
                y.coordinates(width)
            }.shuffled().subList(0, mineCount)
        )

    private fun Int.coordinates(maxWidth: Int): List<Coordinate> = (MIN_VALUE until maxWidth).map { x -> Coordinate(this, x) }

    companion object {
        private const val MIN_VALUE = 0
    }
}
