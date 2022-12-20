package domain.strategy

import domain.Coordinate
import domain.Field
import domain.Fields
import domain.Height
import domain.Land
import domain.Mine
import domain.MineCnt
import domain.Width

class RandomMineBoardGenerateStrategy : BoardGenerateStrategy {
    override fun generate(height: Height, width: Width, mineCnt: MineCnt): Fields {
        val mineCoordinates = getMineCoordinates(height, width, mineCnt)
        return generateFields(height, width, mineCoordinates)
    }

    private fun getMineCoordinates(height: Height, width: Width, mineCnt: MineCnt): List<Coordinate> {
        return (INITIAL_COORDINATE_SIZE until getMaxCoordinateSize(height, width)).shuffled()
            .take(mineCnt.value)
            .map { number -> Coordinate(getCoordinateHeight(number, width), getCoordinateWidth(number, width)) }
    }

    private fun generateFields(height: Height, width: Width, mineCoordinate: List<Coordinate>): Fields {
        return Fields(generateFieldMap(height, width, mineCoordinate))
    }

    private fun generateFieldMap(height: Height, width: Width, mineCoordinate: List<Coordinate>): Map<Coordinate, Field> {
        val map = mutableMapOf<Coordinate, Field>()
        repeat(getMaxCoordinateSize(height, width)) { number ->
            val coordinate = Coordinate(getCoordinateHeight(number, width), getCoordinateWidth(number, width))
            when (coordinate in mineCoordinate) {
                true -> map[coordinate] = Mine()
                false -> map[coordinate] = Land()
            }
        }
        return map
    }

    private fun getCoordinateHeight(number: Int, width: Width) = number / width.value

    private fun getCoordinateWidth(number: Int, width: Width) = number % width.value

    private fun getMaxCoordinateSize(height: Height, width: Width): Int = height.value * width.value

    companion object {
        private const val INITIAL_COORDINATE_SIZE = 0
    }
}
