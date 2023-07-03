package minesweeper.domain

@JvmInline
value class PositiveNumber(val number: Int) {
    init {
        require(number > 0) {
            "0 보다 큰 값만 사용할 수 있습니다."
        }
    }

    infix operator fun times(other: PositiveNumber) = PositiveNumber(this.number * other.number)

    operator fun compareTo(other: PositiveNumber): Int {
        return this.number.compareTo(other.number)
    }

    companion object {
        fun Int.asPositiveNumber() = PositiveNumber(this)
    }
}
