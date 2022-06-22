package minesweeper.domain

import minesweeper.domain.field.Coordinate
import minesweeper.domain.field.CoordinateValue
import minesweeper.domain.field.Dot
import minesweeper.domain.field.Mine
import minesweeper.domain.field.NonMine
import minesweeper.domain.vo.Height
import minesweeper.domain.vo.NumberOfMine
import minesweeper.domain.vo.Width

class MineField(
    val fields: Map<Coordinate, Dot>
) {
    init {
        require(fields.isNotEmpty()) { "지뢰판은 비어있을수 없습니다." }
    }

    fun open(coordinate: Coordinate): Dot = fields[coordinate]
        ?: throw IllegalArgumentException("지뢰 판을 벗어난 좌표는 입력할수 없습니다.")

    companion object {
        private const val START_INDEX = 0

        fun create(height: Height, width: Width, numberOfMine: NumberOfMine, mineCoordinateGenerator: MineCoordinateGenerator): MineField {
            require(height.value * width.value >= numberOfMine.value) { "지뢰 판 크기보다 지뢰 수가 많을수 없습니다." }

            val coordinates = generateCoordinates(height, width)
            val mineCoordinates = mineCoordinateGenerator.generate(coordinates, numberOfMine)
            val fields = generateFields(coordinates, mineCoordinates)

            return MineField(fields.toMap())
        }

        private fun generateCoordinates(
            height: Height,
            width: Width
        ): List<Coordinate> = (START_INDEX until height.value).flatMap { x ->
            (START_INDEX until width.value).map { y -> Coordinate(CoordinateValue(y), CoordinateValue(x)) }
        }

        private fun generateFields(
            coordinates: List<Coordinate>,
            mineCoordinates: List<Coordinate>
        ): Map<Coordinate, Dot> = coordinates.associate {
            if (it in mineCoordinates) {
                it to Mine
            } else {
                it to NonMine.DEFAULT
            }
        }.mapValues { (coordinate, dot) ->
            when(dot) {
                is Mine -> dot
                is NonMine -> NonMine(
                    coordinate.findAround().count(mineCoordinates::contains)
                )
            }
        }
    }
}
