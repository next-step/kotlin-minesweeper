package minesweeper.domain.cell

class MineCell(override val isOpen: Boolean = false) : Cell {

    override val isMine = true

    override fun open() = MineCell(isOpen = true)

    override fun flag(): Cell {
        check(!isOpen) { "이미 open된 Cell입니다." }

        return FlagCell(this)
    }
}
