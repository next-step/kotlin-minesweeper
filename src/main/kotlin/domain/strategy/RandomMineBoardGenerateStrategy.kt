package domain.strategy

import domain.Coordinate
import domain.Field
import domain.FieldType
import domain.Fields
import domain.Land
import domain.Mine
import domain.MineCnt

class RandomMineBoardGenerateStrategy : BoardGenerateStrategy {
    override fun generate(maxCoordinateSize: Int, mineCnt: MineCnt): Fields {
        val mineCoordinates = getMineCoordinates(maxCoordinateSize, mineCnt)
        return generateBoard(maxCoordinateSize, mineCoordinates)
    }

    private fun getMineCoordinates(maxCoordinateSize: Int, mineCnt: MineCnt): List<Coordinate> {
        return (INITIAL_COORDINATE_SIZE until maxCoordinateSize).shuffled()
            .take(mineCnt.value)
            .map { number -> Coordinate(number) }
    }

    private fun generateBoard(maxCoordinateSize: Int, mineCoordinate: List<Coordinate>): Fields {
        return Fields(generateFieldsMap(maxCoordinateSize, mineCoordinate))
    }

    private fun generateFieldsMap(maxCoordinateSize: Int, mineCoordinate: List<Coordinate>): Map<Coordinate, Field> {
        return List(maxCoordinateSize) { number ->
            val coordinate = Coordinate(number)
            when (coordinate in mineCoordinate) {
                true -> coordinate to Mine(coordinate, FieldType.MINE)
                false -> coordinate to Land(coordinate, FieldType.LAND)
            }
        }.toMap()
    }

    companion object {
        private const val INITIAL_COORDINATE_SIZE = 0
    }
}
