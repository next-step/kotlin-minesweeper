package minesweeper.domain

class BlockTable(
    private val _record: MutableMap<MapCord, Block>
) {

    val record
        get() = _record.toMap()

    init {
        setUp()
    }

    private fun setUp() {
        record.keys.forEach { cord ->
            val nearCords = MapCords.from(cord)
                .mapCords
            val nearBlocks = nearCords.mapNotNull { _record[it] }

            _record[cord]?.addNearBlocks(nearBlocks)
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
