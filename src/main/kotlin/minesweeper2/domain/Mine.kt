package minesweeper2.domain

class Mine(val value: Int, rows: Int, cols: Int) {
    init {
        require(value > 0) { "지뢰의 수는 0보다 커야 합니다." }
        require(value < cols * rows) { "지뢰의 수는 높이와 너비의 곱보다 작아야 합니다." }
    }
}
