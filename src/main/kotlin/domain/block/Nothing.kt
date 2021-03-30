package domain.block

data class Nothing(private val surroundingMineCount: Int = 0) : Block() {

    init {
        require(surroundingMineCount in MIN_SURROUNDING_MINE_COUNT..MAX_SURROUNDING_MINE_COUNT) { "주변 지뢰의 개수는 $MIN_SURROUNDING_MINE_COUNT~$MAX_SURROUNDING_MINE_COUNT 사이의 값이어야 합니다. surroundingMineCount: $surroundingMineCount" }
    }

    override fun isMine() = false

    override fun surroundingMineCount() = surroundingMineCount

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Nothing

        if (surroundingMineCount != other.surroundingMineCount) return false

        return true
    }

    override fun hashCode(): Int {
        return surroundingMineCount
    }

    companion object {
        private const val MIN_SURROUNDING_MINE_COUNT = 0
        private const val MAX_SURROUNDING_MINE_COUNT = 8
    }
}
