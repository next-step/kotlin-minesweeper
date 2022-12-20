package domain

data class NormalBlock(override val position: Position) : Block {
    override fun isMine(): Boolean = false
}
