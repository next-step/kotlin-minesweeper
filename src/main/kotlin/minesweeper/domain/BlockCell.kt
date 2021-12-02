package minesweeper.domain

class BlockCell(override val aroundMineCount: MineCount) : Cell {

    override fun isMine() = false
}
