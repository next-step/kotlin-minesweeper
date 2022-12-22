package domain

data class NormalBlock(
    override val position: Position,
    override val mineCount: MineCount
) : Block {
    override fun isMine(): Boolean = false

}
