package minesweeper.domain

class MineCount(val count: Int) {

    init {
        require(count > 0) { "지뢰는 1개 이상이어야 합니다" }
    }
}
