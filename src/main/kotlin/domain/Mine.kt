package domain

data class Mine(
    override val position: Position,
    override val mineCount: MineCount = MineCount(0),
    override val visible: Boolean = false
) : Block {
    override fun open(): Block = copy(visible = true)
    override fun isMine(): Boolean = true
}
