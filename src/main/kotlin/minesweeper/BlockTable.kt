package minesweeper

class BlockTable(
    val record: Map<Pair<Int, Int>, Block>
) {

    fun setUp() {
        record.keys.forEach { cord ->
            val mineCount = listOf(
                (cord.first - 1 to cord.second - 1),
                (cord.first - 1 to cord.second),
                (cord.first - 1 to cord.second + 1),
                (cord.first to cord.second - 1),
                (cord.first to cord.second + 1),
                (cord.first + 1 to cord.second),
                (cord.first + 1 to cord.second + 1),
            ).count { record[it] is MineBlock }

            record[cord]?.setNearbyMineCount(mineCount)
        }
    }
}
