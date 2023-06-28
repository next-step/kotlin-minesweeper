package minesweeper.domain

@JvmInline
value class Width(private val width: String) {
    init {
        require(width.toIntOrNull() != null) {
            "너비는 숫자이어야함"
        }

        require(width.toInt () > MINIMUM_WIDTH) {
            "너비는 0보다 커야함"
        }
    }

    fun getWidth(): Int {
        return width.toInt()
    }

    companion object {
        const val MINIMUM_WIDTH = 0
    }
}
