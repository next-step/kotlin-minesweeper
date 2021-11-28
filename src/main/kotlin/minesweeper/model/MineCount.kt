package minesweeper.model

@JvmInline
value class MineCount private constructor(val value: Int) {

    companion object {
        val ZERO: MineCount = MineCount(0)

        fun valueOf(value: Int?): MineCount = value?.coerceAtLeast(0)?.let(::MineCount) ?: ZERO
    }
}
