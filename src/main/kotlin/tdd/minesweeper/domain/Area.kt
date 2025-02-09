package tdd.minesweeper.domain

data class Area(val height: Int, val width: Int) {
    init {
        require(height > 0) {
            "높이는 양수여야 합니다: height=$height"
        }
        require(width > 0) {
            "너비는 양수여야 합니다: width=$width"
        }
    }
}
