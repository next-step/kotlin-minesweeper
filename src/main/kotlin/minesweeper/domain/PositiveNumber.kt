package minesweeper.domain

@JvmInline
value class PositiveNumber(val value: Int) {
    init {
        require(value > 0) { "양수여야 합니다." }
    }

    operator fun times(input: PositiveNumber): PositiveNumber {
        val result = value.times(input.value)
        return PositiveNumber(result)
    }

    companion object {
        fun from(value: String) = PositiveNumber(value.toInt())
    }
}
