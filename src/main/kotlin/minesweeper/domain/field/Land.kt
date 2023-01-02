package minesweeper.domain.field

interface Land {
    fun mine(): Land
    fun safe(aroundMineCount: Int): Land
    fun aroundMineCount():Int

    override fun toString(): String
}