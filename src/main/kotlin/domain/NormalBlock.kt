package domain

data class NormalBlock(
    override val position: Position,
    override val mineCount: MineCount,
    override val visible: Boolean = false
) : Block {
    override fun isMine(): Boolean = false
    override fun open(): Block = copy(visible = true)
}
