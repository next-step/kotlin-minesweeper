package minesweeper.domain.realBoard

@JvmInline
value class Width(private val width: Int) {

    companion object {
        fun of(width: Int) : Width = Width(width)
    }
}
