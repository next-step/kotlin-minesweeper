package minesweeper

class MineMap(
    val mineMapInfo: MineMapInfo,
    createStrategy: MinePointCreateStrategy = RandomPointCreateStrategy()
) {
    private val mineList: MineList =
        MineList.createMineList(mineMapInfo, createStrategy)
    val mineMap: Map<Point, MapTile> = mutableMapOf<Point, MapTile>().apply {
        for (mine in mineList.mineList) {
            this[mine] = MapTile.Mine
        }

        for (mine in mineList.mineList) {
            createNear(this, mine)
        }
    }

    private fun createNear(map: MutableMap<Point, MapTile>, mine: Point) {
        val adjacentPoints = AdjacentPoints.create(mine, mineMapInfo.rowCnt, mineMapInfo.colCnt)
        for (adj in adjacentPoints.points) {
            val nearInfo = map.getOrDefault(adj, MapTile.Blank(0))
            if (nearInfo is MapTile.Blank) map[adj] = nearInfo + 1
        }
    }
}
