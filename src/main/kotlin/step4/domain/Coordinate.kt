package step4.domain

data class Coordinate(
    val row: Int,
    val column: Int,
) {
    init {
        require(row >= 0) { "row는 0미만의 값이 입력될 수 없습니다." }
        require(column >= 0) { "column은 0미만의 값이 입력될 수 없습니다." }
    }

    fun up(): Coordinate = Coordinate(row - 1, column)

    fun down(): Coordinate = Coordinate(row + 1, column)

    fun right(): Coordinate = Coordinate(row, column + 1)

    fun left(): Coordinate = Coordinate(row, column - 1)
}
