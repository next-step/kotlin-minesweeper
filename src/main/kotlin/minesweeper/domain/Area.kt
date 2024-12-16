package minesweeper.domain

data class Area(val height: Int, val width: Int) {
    init {
        require(height > 0) {
            "영역의 높이는 1 이상이어야 합니다: height=$height"
        }
        require(width > 0) {
            "영역의 높이는 1 이상이어야 합니다: width=$width"
        }
    }
}
