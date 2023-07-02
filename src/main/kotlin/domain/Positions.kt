package domain

fun List<Position>.toPositions() = Positions(this)
data class Positions(val value: List<Position>) {
    fun getMineCountCompareSet(
        minePositionSet: Set<Position>,
        makeMineCount: (count: Int) -> MineCountNumber,
    ): MineCountNumber {
        return value.count { minePositionSet.contains(it) }.run {
            makeMineCount(this)
        }
    }
}
