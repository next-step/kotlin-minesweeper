package minesweeper.domain.realBoard

@JvmInline
value class Height(private val height: Int) {
    companion object {
        fun of(height: Int): Height = Height(height)
    }
}
