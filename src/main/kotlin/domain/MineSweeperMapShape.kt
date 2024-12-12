package domain

class MineSweeperMapShape(val width: Int, val height: Int) {
    init {
        require(width > 0) { "너비는 양수여야함" }
        require(height > 0) { "높이는 양수여야함" }
    }
}
