package minesweeper.domain

@JvmInline
value class PositiveNumber(private val number: Int) {
    init {
        require(number > 0) {
            "0 보다 큰 값만 사용할 수 있습니다."
        }
    }

    companion object {
        fun Int.asPositiveNumber() = PositiveNumber(this)
    }
}
