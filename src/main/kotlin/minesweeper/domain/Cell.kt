package minesweeper.domain

interface Cell {

    fun isMine(): Boolean

    val aroundMineCount: MineCount
}
