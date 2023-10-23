package step4.view

import step4.domain.coordinate.Coordinate

data class CoordinateView(
    val row: Int,
    val column: Int,
) {
    fun toCoordinate(): Coordinate = Coordinate(row = row, column = column)

    companion object {
        fun from(value: String): CoordinateView {
            val coordinate = value.split(",")
                .map { it.trim() }
            require(coordinate.size == 2) { "좌표는 row, column 2개만 입력가능합니다." }
            return CoordinateView(
                row = parseValue(coordinate, 0),
                column = parseValue(coordinate, 1),
            )
        }

        private fun parseValue(coordinate: List<String>, index: Int) =
            coordinate[index].toIntOrNull() ?: throw IllegalArgumentException("숫자만 입력가능합니다.")
    }
}
