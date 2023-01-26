package model

class Mine(val size: Int) {
    init {
        require(size > 0) { "지뢰 개수는 0보다 커야 합니다." }
    }
}
