package minesweeper.domain

data class Area(val height: Int, val width: Int) {
    init {
        require(height > 0) { "높이는 0보다 커야합니다. ( current : $height )" }
        require(width > 0) { "너비는 0보다 커야합니다. ( current : $width )" }
    }
}
