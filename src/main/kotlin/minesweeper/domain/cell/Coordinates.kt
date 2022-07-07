package minesweeper.domain.cell

import minesweeper.domain.MineBoardLength
import minesweeper.domain.MineCount

class Coordinates(
    val values: List<Coordinate>,
) {
    fun randomMine(count: MineCount): Map<Coordinate, Dot> {
        val mineCoordinates = shuffleAndTake(count.value)
        return mineAt(mineCoordinates)
    }

    private fun shuffleAndTake(count: Int): List<Coordinate> {
        require(values.size >= count) { "좌표 개수보다 지뢰 개수가 많을 수 없습니다." }
        return values.shuffled().take(count)
    }

    private fun mineAt(mineCoordinates: List<Coordinate>) = values.associate { coordinate ->
        if (coordinate in mineCoordinates) {
            coordinate to Mine
        } else {
            coordinate to Land(mineCount(coordinate, mineCoordinates))
        }
    }

    private fun mineCount(
        coordinate: Coordinate, mineCoordinates: List<Coordinate>
    ) = MineCount(CoordinateDirection.aroundCoordinates(coordinate).count { it in mineCoordinates })

    companion object {
        private const val START_INDEX = 0

        fun from(height: MineBoardLength, width: MineBoardLength): Coordinates = Coordinates(
            (START_INDEX until height.value).flatMap { xValue ->
                generateRows(width, xValue)
            }
        )

        private fun generateRows(width: MineBoardLength, xValue: Int) =
            (START_INDEX until width.value).map { yValue ->
                Coordinate(y = CoordinateValue(value = yValue), x = CoordinateValue(value = xValue)
            )
        }
    }
}
