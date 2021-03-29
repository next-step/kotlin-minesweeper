package domain

data class Position(
    val row: Int,
    val col: Int
) {
    init {
        require(row >= 0 && col >= 0) { "열과 행은 0보다 작을 수 없습니다." }
    }
}
