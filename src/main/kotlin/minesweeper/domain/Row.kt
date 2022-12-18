package minesweeper.domain

class Row(val count: Int) {

    init {
        require(count > 0) { "높이는 1 이상이어야 합니다" }
    }
}
