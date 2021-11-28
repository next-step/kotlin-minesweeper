package minesweeper.model

@JvmInline
value class MineCount(val count: Int) {

    init {
        require(count >= 0)
    }

    companion object {
        val ZERO: MineCount = MineCount(0)
    }
}
