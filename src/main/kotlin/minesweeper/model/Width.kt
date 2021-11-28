package minesweeper.model

@JvmInline
value class Width private constructor(val value: Int) {

    companion object {
        val ZERO = Width(0)

        fun valueOf(value: Int?): Width = value?.coerceAtLeast(0)?.let(::Width) ?: ZERO
    }
}
