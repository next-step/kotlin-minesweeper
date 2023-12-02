package minesweeper.domain

data class Width(val value: Int) {
    init {
        require(value > 0) { "너비는 1 이상 이어야 합니다." }
    }
}
