package domain

data class NormalBlock(
    override val position: Position,
    val mineCount: Int
) : Block {
    constructor(xPosition: Int, yPosition: Int, mineCount: Int) : this(Position.of(xPosition, yPosition), mineCount)

    override fun isMine(): Boolean = false

    override fun getMinesCount(): Int = mineCount
}
