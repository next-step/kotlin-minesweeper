package minesweeper.domain.data

@JvmInline
value class PositiveNumber(val number: Int) {

    init {
        require(number > BASE_NUMBER) {
            "PositiveNumber $BASE_NUMBER 보다 커야 합니다."
        }
    }

    operator fun times(num: PositiveNumber): PositiveNumber = PositiveNumber(number * num.number)

    operator fun compareTo(other: Int): Int = compareValuesBy(this.number, other) {
        it
    }

    companion object {
        const val BASE_NUMBER = 0
    }

}
