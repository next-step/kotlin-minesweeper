package minesweeper.domain

@JvmInline
value class Width(private val width: Int) {

    fun getWidth(): Int {
        return width
    }

    companion object {
        private const val MINIMUM_WIDTH = 0
        fun of(value: String): Width {
            require(value.toIntOrNull() != null) {
                "너비는 숫자이어야함"
            }
            val width = value.toInt()
            require(width > MINIMUM_WIDTH) {
                "너비는 숫자이어야함"
            }
            return Width(width)
        }
    }
}
