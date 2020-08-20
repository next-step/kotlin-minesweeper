package domain.block

data class OpenedBlock(
    override val position: Position,
    private val mineCount: Int
) : Block {

    constructor(x: Int, y: Int, mineCount: Int) : this(Position.of(x, y), mineCount)

    override fun isMine(): Boolean = false

    override fun isClosed(): Boolean = false

    override fun getMinesCount(): Int = mineCount
}
