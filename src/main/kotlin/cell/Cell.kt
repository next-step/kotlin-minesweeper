package cell

sealed interface Cell {
    val revealed: Boolean

    fun reveal(): Cell

    fun shouldFloodFill(): Boolean
}

data class BlankCell(
    val adjacentMineCount: MineCount,
    private var _revealed: Boolean = false,
) : Cell {
    override val revealed: Boolean
        get() = _revealed

    override fun reveal(): Cell {
        _revealed = true
        return this
    }

    override fun shouldFloodFill(): Boolean = adjacentMineCount == MineCount.ZERO
}

data object MineCell : Cell {
    private var _revealed: Boolean = false
    override val revealed: Boolean
        get() = _revealed

    override fun reveal(): Cell {
        _revealed = true
        return this
    }

    override fun shouldFloodFill(): Boolean = false
}
