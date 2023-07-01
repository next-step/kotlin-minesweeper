package minesweeper.domain

@JvmInline
value class Height(private val height: Int) {

    fun getHeight(): Int {
        return height
    }

    companion object {
        private const val MINIMUM_HEIGHT = 0
        fun of(value: String): Height {
            require(value.toIntOrNull() != null) {
                "높이는 숫자이어야함"
            }
            val height = value.toInt()
            require(height > MINIMUM_HEIGHT) {
                "높이는 0보다 커야함"
            }
            return Height(height)
        }
    }
}
