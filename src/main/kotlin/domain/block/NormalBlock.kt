package domain.block

data class NormalBlock(
    override val position: Position,
    private val mineCount: Int,
    override val isClose: Boolean = true
) : Block {
    constructor(x: Int, y: Int, mineCount: Int) : this(Position.of(x, y), mineCount)

    override fun isMine(): Boolean = false

    override fun getMinesCount(): Int = mineCount

    override fun open(): Block = this.copy(isClose = false)
}
