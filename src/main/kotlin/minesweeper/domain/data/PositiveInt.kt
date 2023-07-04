package minesweeper.domain.data

@JvmInline
value class PositiveInt(val number: Int) {

    init {
        require(number > 0) {
            "PositiveInt 0 보다 커야 합니다."
        }
    }

    operator fun times(num: PositiveInt): PositiveInt = PositiveInt(number * num.number)

    operator fun compareTo(other: PositiveInt): Int = compareValuesBy(this, other) {
        it.number
    }

}
