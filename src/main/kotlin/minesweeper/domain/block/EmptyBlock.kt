package minesweeper.domain.block

data class EmptyBlock(override val position: Position) : Block(position) {
    override fun isMines(): Boolean = false
}
