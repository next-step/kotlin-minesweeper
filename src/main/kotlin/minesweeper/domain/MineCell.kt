package minesweeper.domain

class MineCell : Cell {

    override val aroundMineCount: MineCount
        get() = throw UnsupportedOperationException()

    override fun isMine() = true
}
