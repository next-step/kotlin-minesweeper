package minesweeper

class BlockTable(
    val record: Map<Cord, Block>
) {

    fun setUp() {
        record.keys.forEach { cord ->
            val mineCount = Cords.from(cord)
                .cords
                .count { record[it] is MineBlock }

            record[cord]?.setNearbyMineCount(mineCount)
        }
    }

    companion object {
        fun of(height: Int, width: Int, mineCount: Int): BlockTable {
            val cords = Cords.of(height, width)
            val blocks = List(height * width - mineCount) { CleanBlock() } +
                List(mineCount) { MineBlock() }

            return BlockTable(
                cords.cords.zip(blocks) { cord, block ->
                    cord to block
                }.toMap()
            )
        }
    }
}
