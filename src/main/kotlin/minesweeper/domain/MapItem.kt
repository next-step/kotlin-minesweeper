package minesweeper.domain

sealed class MapItem {
    abstract val item: String

    companion object {
        const val MINE_SYMBOL = "*"
        const val EMPTY_SYMBOL = "C"
    }
}

data class Mine(
    override val item: String = MINE_SYMBOL,
) : MapItem()

data class Empty(
    override val item: String = EMPTY_SYMBOL,
) : MapItem()
