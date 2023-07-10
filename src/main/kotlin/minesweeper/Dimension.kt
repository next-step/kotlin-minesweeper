package minesweeper

@JvmInline
value class Dimension private constructor(val value: Int) {
    companion object {
        fun of(data: String): Dimension {

            val value = data.toIntOrNull()
            require(value != null) {
                "데이터는 숫자이어야한다"
            }
            require(value > 0) {
                "데이터는 0보다 커야한다"
            }
            return Dimension(value)
        }
    }
}
