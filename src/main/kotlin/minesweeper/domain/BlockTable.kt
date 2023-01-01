package minesweeper.domain

class BlockTable(
    val record: Map<MapCord, Block>
) {

    init {
        setUp()
    }

    /**
     * 해당 좌표의 블록을 열고 결과값을 반환한다
     * 게임이 클리어 되었을 때는 선택한 블록의 결과값이 아닌 승리를 반환한다.
     */
    fun open(mapCord: MapCord): GameState {
        val selectedBlock = record[mapCord] ?: throw IllegalArgumentException("")
        val result = selectedBlock.open()

        if (isGameClear()) return GameState.WIN
        return result
    }

    private fun setUp() {
        record.keys.forEach { cord ->
            val nearCords = MapCords.from(cord)
                .mapCords
            val nearBlocks = nearCords.mapNotNull { record[it] }

            record[cord]?.addNearBlocks(nearBlocks)
        }
    }

    private fun isGameClear(): Boolean {
        return record.values
            .filterIsInstance<CleanBlock>()
            .all { it.isOpen() }
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
