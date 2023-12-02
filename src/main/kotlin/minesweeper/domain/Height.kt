package minesweeper.domain

data class Height(val value: Int) {
    init {
        require(value > 0) { "높이는 1 이상 이어야 합니다." }
    }
}
