package minesweeper.domain.model

@JvmInline
value class Width private constructor(private val value: Int) {

    init {
        require(value in MIN_WIDTH..MAX_WIDTH) {
            "넓이는 ${MIN_WIDTH}보다 크고 ${MAX_WIDTH}보다 작아야 한다."
        }
    }

    fun toInt(): Int = value

    companion object {
        private const val MIN_WIDTH = 1
        private const val MAX_WIDTH = 1000

        fun from(width: Int): Width = Width(width)
    }
}
