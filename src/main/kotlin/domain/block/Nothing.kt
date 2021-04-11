package domain.block

data class Nothing(private val surroundingMineCount: SurroundingMineCount = SurroundingMineCount(0)) : Block() {

    constructor(surroundingMineCount: Int) : this(SurroundingMineCount(surroundingMineCount))

    override fun isMine() = false

    override fun isZero() = surroundingMineCount == SurroundingMineCount(0)

    override fun surroundingMineCount() = surroundingMineCount
}
