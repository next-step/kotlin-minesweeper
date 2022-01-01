package minesweeper.domain.cell

import minesweeper.domain.board.MineCount

class BlockCell(
    val aroundMineCount: MineCount,
    override val isOpen: Boolean = false,
) : Cell {

    override val isMine = false

    val hasNoMineAround = (aroundMineCount == MineCount.ZERO)

    override fun open(): Cell {
        check(!isOpen) { "이미 open된 Cell입니다." }

        return BlockCell(aroundMineCount, isOpen = true)
    }

    override fun flag(): Cell {
        check(!isOpen) { "이미 open된 Cell입니다." }

        return FlagCell(this)
    }
}

