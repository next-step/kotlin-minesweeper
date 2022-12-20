package domain.strategy

import domain.Coordinate
import domain.Field
import domain.Fields
import domain.Land
import domain.Mine
import domain.MineCnt

class RandomMineBoardGenerateStrategy : BoardGenerateStrategy {
    override fun generate(maxCoordinateSize: Int, mineCnt: MineCnt): Fields {
        val mineCoordinates = getMineCoordinates(maxCoordinateSize, mineCnt)
        return generateFields(maxCoordinateSize, mineCoordinates)
    }

    private fun getMineCoordinates(maxCoordinateSize: Int, mineCnt: MineCnt): List<Coordinate> {
        return (INITIAL_COORDINATE_SIZE until maxCoordinateSize).shuffled()
            .take(mineCnt.value)
            .map { number -> Coordinate(number) }
    }

    private fun generateFields(maxCoordinateSize: Int, mineCoordinate: List<Coordinate>): Fields {
        return Fields(generateFieldMap(maxCoordinateSize, mineCoordinate))
    }

    private fun generateFieldMap(maxCoordinateSize: Int, mineCoordinate: List<Coordinate>): Map<Coordinate, Field> {
        val map = mutableMapOf<Coordinate, Field>()
        repeat(maxCoordinateSize) { number ->
            val coordinate = Coordinate(number)
            when (coordinate in mineCoordinate) {
                true -> map[coordinate] = Mine()
                false -> map[coordinate] = Land()
            }
        }
        return map
    }

    companion object {
        private const val INITIAL_COORDINATE_SIZE = 0
    }
}
