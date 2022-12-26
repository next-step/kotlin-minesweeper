package minesweeper.domain

class Matrix(val rows: List<MutableList<Field>>) {
    fun land(coordinate: Coordinate, field: Field) {
        validCoordinate(coordinate)

        this.rows[coordinate.rows][coordinate.cols] = field
    }

    fun field(coordinate: Coordinate): Field {
        validCoordinate(coordinate)

        return this.rows[coordinate.rows][coordinate.cols]
    }

    private fun validCoordinate(coordinate: Coordinate) {
        require(coordinate.rows in rows.indices && coordinate.cols in rows[coordinate.rows].indices) {
            "지도 크기를 벗어나는 지역을 조회할 수는 없습니다."
        }
    }

    fun width() = rows[0].size

    fun height() = rows.size

    companion object {
        fun of(width: Int, height: Int) = Matrix(createRows(height, width))

        private fun createRows(height: Int, width: Int) = List(height) { createCols(width) }

        private fun createCols(width: Int) = MutableList(width) { Field() }
    }
}
