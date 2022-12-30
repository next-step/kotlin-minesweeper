package minesweeper

class BlockTable(
    val record: Map<MapCord, Block>
) {

    fun setUp() {
        record.keys.forEach { cord ->
            val mineCount = MapCords.from(cord)
                .mapCords
                .count { record[it] is MineBlock }

            record[cord]?.setNearbyMineCount(mineCount)
        }
    }

    companion object {
        fun of(height: Int, width: Int, mineCount: Int): BlockTable {
            val mapCords = MapCords.of(height, width)
            val blocks = Blocks.of(height * width, mineCount)
                .shuffle()

            return BlockTable(
                mapCords.mapCords.zip(blocks.blocks) { cord, block ->
                    cord to block
                }.toMap()
            )
        }
    }
}
