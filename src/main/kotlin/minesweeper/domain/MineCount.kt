package minesweeper.domain

data class MineCount(val count: Int) {
    init {
        require(count > 0) { "지뢰는 1 개 이상 있어야 합니다" }
    }
}
