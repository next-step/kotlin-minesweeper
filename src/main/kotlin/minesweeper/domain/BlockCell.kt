package minesweeper.domain

class BlockCell(val aroundMineCount: MineCount) : Cell {

    override fun isMine() = false
}
