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

    companion object {
        fun of(height: Int, width: Int, mineCount: Int): BlockTable {
            val cords = (0 until height).map { y ->
                (0 until width).map { x -> (y to x) }
            }.flatten()
            val blocks = List(height * width - mineCount) { CleanBlock() } +
                List(mineCount) { MineBlock() }

            return BlockTable(
                cords.zip(blocks) { cord, block ->
                    cord to block
                }.toMap()
            )
        }
    }
}
