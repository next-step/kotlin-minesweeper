package domain

data class Mine(
    override val position: Position,
    override val mineCount: MineCount = MineCount(0),
    override val isVisible: Boolean = false
) : Block {
    override fun open(): Block = copy(isVisible = true)
    override fun isMine(): Boolean = true
}
