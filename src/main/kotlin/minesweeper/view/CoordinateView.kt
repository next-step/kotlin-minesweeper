package minesweeper.view

import minesweeper.domain.cell.Coordinate

data class CoordinateView(
    val row: Int,
    val column: Int,
) {
    fun toCoordinate(): Coordinate = Coordinate(row = row, column = column)

    companion object {
        private const val COORDINATE_DELIMITER = ","
        private const val COORDINATE_VIEW_SIZE = 2

        fun from(value: String): CoordinateView {
            val coordinateValues = value.split(COORDINATE_DELIMITER)
                .map { it.trim() }
            require(coordinateValues.size == COORDINATE_VIEW_SIZE) { "좌표는 row, column 2개만 입력가능합니다." }
            val row = coordinateValues[0].toIntOrNull() ?: throw IllegalArgumentException("숫자만 입력가능합니다.")
            val column = coordinateValues[1].toIntOrNull() ?: throw IllegalArgumentException("숫자만 입력가능합니다.")
            return CoordinateView(row, column)
        }
    }
}
