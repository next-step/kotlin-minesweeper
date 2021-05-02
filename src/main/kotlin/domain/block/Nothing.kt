package domain.block

class Nothing(
    private val surroundingMineCount: SurroundingMineCount = SurroundingMineCount(0),
    isOpened: Boolean = false
) : Block(isOpened) {

    constructor(surroundingMineCount: Int) : this(SurroundingMineCount(surroundingMineCount))

    override fun makeOpenedBlock(): Block {
        return Nothing(surroundingMineCount, true)
    }

    override fun isMine() = false

    override fun isZero() = surroundingMineCount.isZero()

    override fun getSurroundingMineCount() = surroundingMineCount.value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Nothing

        if (surroundingMineCount != other.surroundingMineCount) return false

        return true
    }

    override fun hashCode(): Int {
        return surroundingMineCount.hashCode()
    }
}
