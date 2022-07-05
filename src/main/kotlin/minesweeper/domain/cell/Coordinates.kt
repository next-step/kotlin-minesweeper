package minesweeper.domain.cell

import minesweeper.domain.MineBoardLength
import minesweeper.domain.MineCount

class Coordinates(
    val values: List<Coordinate>,
) {
    fun randomMine(count: MineCount): List<Cell> {
        val mineCoordinates = shuffleAndTake(count.value)
        return mineAt(mineCoordinates)
    }

    private fun shuffleAndTake(count: Int): List<Coordinate> {
        require(values.size >= count) { "좌표 개수보다 지뢰 개수가 많을 수 없습니다." }
        return values.shuffled()
            .take(count)
    }

    private fun mineAt(mineCoordinates: List<Coordinate>) =
        values.map {
            if (it in mineCoordinates) {
                Cell(it, Mine)
            } else {
                Cell(it, Land)
            }
        }

    companion object {
        private const val START_INDEX = 0

        fun from(height: MineBoardLength, width: MineBoardLength): Coordinates = Coordinates(
            (START_INDEX until height.value).flatMap { xValue ->
                (START_INDEX until width.value).map { yValue ->
                    Coordinate(
                        y = CoordinateValue(value = yValue),
                        x = CoordinateValue(value = xValue)
                    )
                }
            }
        )
    }
}
