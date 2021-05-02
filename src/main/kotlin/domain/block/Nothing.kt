package domain.block

class Nothing(
    private val surroundingMineCount: SurroundingMineCount = SurroundingMineCount(0),
    isChecked: Boolean = false
) : Block(isChecked) {

    constructor(surroundingMineCount: Int) : this(SurroundingMineCount(surroundingMineCount))

    override fun makeCheckedBlock(): Block {
        return Nothing(surroundingMineCount, true)
    }

    override fun isMine() = false

    override fun isZero() = surroundingMineCount.isZero()

    override fun surroundingMineCount() = surroundingMineCount

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
