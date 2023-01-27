package model

class BoardWidth(val width: Int) {
    init {
        require(width > 0) { "너비는 0보다 커야 합니다." }
    }
}
