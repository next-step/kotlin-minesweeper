package minesweeper.domain.model

@JvmInline
value class Height private constructor(private val value: Int) {

    init {
        require(value in MIN_HEIGHT..MAX_HEIGHT) {
            "높이는 ${MIN_HEIGHT}보다 크고 ${MAX_HEIGHT}보다 작아야 한다."
        }
    }

    fun toInt(): Int = value

    companion object {
        private const val MIN_HEIGHT = 1
        private const val MAX_HEIGHT = 1000

        fun from(height: Int): Height = Height(height)
    }
}
