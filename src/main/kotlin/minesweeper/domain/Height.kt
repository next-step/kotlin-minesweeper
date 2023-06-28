package minesweeper.domain

@JvmInline
value class Height(private val height: String) {
    init {
        require(height.toIntOrNull() != null) {
            "높이는 숫자이어야함"
        }

        require(height.toInt () > MINIMUM_HEIGHT) {
            "높이는 0보다 커야함"
        }
    }

    fun getHeight(): Int {
        return height.toInt()
    }

    companion object {
        const val MINIMUM_HEIGHT = 0
    }
}
