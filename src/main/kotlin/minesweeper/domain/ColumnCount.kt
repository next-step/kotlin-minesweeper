package minesweeper.domain

class ColumnCount(val count: Int) {

    init {
        require(count > 0) { "너비는 1 이상이어야 합니다" }
    }
}
