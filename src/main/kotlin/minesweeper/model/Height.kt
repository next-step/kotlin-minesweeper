package minesweeper.model

@JvmInline
value class Height private constructor(val value: Int) {

    companion object {
        val ZERO = Height(0)

        fun valueOf(value: Int?): Height = value?.coerceAtLeast(0)?.let(::Height) ?: ZERO
    }
}
