package domain

data class Mine(val x: Int, val y: Int) {
    init {
        require(x >= 0) { "x는 0보다 작을 수 없습니다." }
        require(y >= 0) { "y는 0보다 작을 수 없습니다." }
    }
}
