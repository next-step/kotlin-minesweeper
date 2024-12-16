package minesweeper.domain

class Rows(private val values: List<Row>) : List<Row> by values {
    fun find(location: Location): Cell? {
        val rowIndex = location.row - 1
        require(rowIndex in values.indices) {
            "해당 Rows 가 가진 행의 범위를 벗어났습니다: location.row=${location.row}, values.indices=${values.indices}"
        }

        return values[rowIndex].find(location)
    }
}
