package minesweeper.model

@JvmInline
value class MineCount(val count: Int) {

    init {
        require(count >= 0)
    }

    fun coerceAtMost(count: Int): MineCount = MineCount(this.count.coerceAtMost(count))

    companion object {
        val ZERO: MineCount = MineCount(0)
    }
}
