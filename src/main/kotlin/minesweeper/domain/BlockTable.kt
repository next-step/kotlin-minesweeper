package minesweeper.domain

class BlockTable(
    val record: Map<MapCord, Block>
) {

    init {
        setUp()
    }

    private fun setUp() {
        record.keys.forEach { cord ->
            val nearCords = MapCords.from(cord)
                .mapCords
            val nearBlocks = nearCords.mapNotNull { record[it] }

            record[cord]?.addNearBlocks(nearBlocks)
        }
    }

    companion object {
        fun of(mapCords: MapCords, blocks: Blocks): BlockTable {
            return BlockTable(
                mapCords.mapCords.zip(blocks.blocks) { cord, block ->
                    cord to block
                }.toMap().toMutableMap()
            )
        }
    }
}
