package minesweeper

class MineMap(
    val mineMapInfo: MineMapInfo,
    createStrategy: MinePointCreateStrategy = RandomPointCreateStrategy()
) {
    private val mineList: MineList =
        MineList.createMineList(mineMapInfo, createStrategy)
    val mineMap: Map<Point, MapTile> = mutableMapOf<Point, MapTile>().apply {
        for (mine in mineList.mineList) {
            this[mine] = MapTile.MINE
        }

        for (mine in mineList.mineList) {
            this[mine] = MapTile.MINE
        }
    }
}

enum class MapTile {
    BLANK, MINE
}
