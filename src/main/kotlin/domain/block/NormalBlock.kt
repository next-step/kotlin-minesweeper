package domain.block

data class NormalBlock(
    override val position: Position,
    val mineCount: Int
) : Block {
    constructor(x: Int, y: Int, mineCount: Int) : this(Position.of(x, y), mineCount)

    override fun isMine(): Boolean = false

    override fun getMinesCount(): Int = mineCount
}
