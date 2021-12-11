package minesweeper.domain.block

data class MineBlock(override val position: Position) : Block(position) {
    override val isMine: Boolean = true
}
