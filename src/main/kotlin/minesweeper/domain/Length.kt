package minesweeper.domain

@JvmInline
value class Length private constructor(val value: Int) {

    companion object {
        private const val MINIMUM_HEIGHT = 0
        fun of(valueString: String): Length {
            require(valueString.toIntOrNull() != null) {
                "값은 숫자이어야함"
            }
            val value = valueString.toInt()
            require(value > MINIMUM_HEIGHT) {
                "값은 0보다 커야함"
            }
            return Length(value)
        }
    }
}
