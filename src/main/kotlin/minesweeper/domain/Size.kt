package minesweeper.domain

data class Size(val value: Int) {
    constructor(value: String) : this(value.toInt())

    init {
        require(value in 1..100) { "1 이상 100 이하의 숫자여야만 합니다." }
    }
}
