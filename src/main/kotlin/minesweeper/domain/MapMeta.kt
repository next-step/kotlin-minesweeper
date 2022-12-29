package minesweeper.domain

class MapMeta(val height: Int, val width: Int, val mineCount: Int) {
    init {
        require(height >= 1) { "높이는 최소 1 이상이여야 합니다." }
        require(width >= 1) { "너비는 최소 1 이상이여야 합니다." }
        require(mineCount >= 1) { "지뢰 갯수는 최소 1 이상이여야 합니다." }
    }
}
