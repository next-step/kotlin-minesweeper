package minesweeper.domain.cell

import minesweeper.domain.board.MineCount

class BlockCell(val aroundMineCount: MineCount) : Cell {

    override fun isMine() = false
}
