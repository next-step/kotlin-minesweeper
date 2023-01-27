package model

class BoardHeight(val height: Int) {
    init {
        require(height > 0) { "높이는 0보다 커야 합니다." }
    }
}
