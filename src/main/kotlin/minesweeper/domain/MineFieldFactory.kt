package minesweeper.domain

import minesweeper.domain.field.*
import minesweeper.domain.vo.Height
import minesweeper.domain.vo.Width

object MineFieldFactory {
    private const val START_INDEX = 0

    fun create(height: Height, width: Width, numberOfMine: Int, mineCoordinateGenerator: MineCoordinateGenerator): MineField {
        require(height.value * width.value >= numberOfMine) { "지뢰 판 크기보다 지뢰 수가 많을수 없습니다." }

        val coordinates = (START_INDEX until height.value).flatMap { x ->
            (START_INDEX until width.value).map { y -> Coordinate(CoordinateValue(y), CoordinateValue(x)) }
        }
        val mineCoordinates = mineCoordinateGenerator.generate(coordinates, numberOfMine)

        return coordinates.map {
            if (it in mineCoordinates) {
                Field(it, Mine)
            } else {
                Field(it, NonMine)
            }
        }.let(::MineField)
    }
}
