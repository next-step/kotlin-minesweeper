package domain

data class Mine(
    override val position: Position,
    override val mineCount: MineCount = MineCount(0)
) : Block {
    override fun isMine(): Boolean = true
}
