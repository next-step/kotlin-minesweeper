package minesweeper.domain.block

data class EmptyBlock(override val position: Position, val value: Int = 0) : Block(position) {
    override fun isMines(): Boolean = false
}
