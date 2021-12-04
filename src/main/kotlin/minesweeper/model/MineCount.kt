package minesweeper.model

@JvmInline
value class MineCount private constructor(val value: Int) {

    fun increment(): MineCount = MineCount(value.inc())

    companion object {
        val ZERO: MineCount = MineCount(0)

        fun valueOf(value: Int?): MineCount = value?.coerceAtLeast(0)?.let(::MineCount) ?: ZERO
    }
}
