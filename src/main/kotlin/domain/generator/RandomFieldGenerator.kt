package domain.generator

import domain.Field
import domain.LocationOfMines
import domain.coord.AbstractCoordinate

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

    private fun Int.coordinates(maxWidth: Int): List<AbstractCoordinate> = (MIN_VALUE until maxWidth).map { x -> AbstractCoordinate(this, x) }

    companion object {
        private const val MIN_VALUE = 0
    }
}
