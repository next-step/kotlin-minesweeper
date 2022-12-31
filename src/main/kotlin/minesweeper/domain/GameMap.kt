package minesweeper.domain

class GameMap(
    val blockTable: BlockTable
) {

    companion object {
        fun of(height: Int, width: Int, mineCount: Int, mineSettingStrategy: MineSettingStrategy): GameMap {
            val mapCords = MapCords.of(height, width)
            val mineBlockLocations = mineSettingStrategy.getLocations(height * width, mineCount)
            val blocks = Blocks.of(height * width, mineBlockLocations)

            return GameMap(
                BlockTable.of(mapCords, blocks)
            )
        }
    }
}
