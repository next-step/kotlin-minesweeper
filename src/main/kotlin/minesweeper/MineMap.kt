package minesweeper

class MineMap(
    val mineMap: Map<Point, MapTile>,
    val mapSize: MapSize
) {
    companion object {
        fun create(
            mineMapInfo: MineMapInfo,
            createStrategy: MinePointCreateStrategy = RandomPointCreateStrategy()
        ): MineMap {
            val mineList: MineList =
                MineList.createMineList(mineMapInfo, createStrategy)

            return MineMap(
                mutableMapOf<Point, MapTile>().apply {
                    for (mine in mineList.mineList) {
                        this[mine] = MapTile.Mine
                    }

                    for (mine in mineList.mineList) {
                        createNear(this, mine, mineMapInfo.rowCnt, mineMapInfo.colCnt)
                    }
                },
                mineMapInfo.mapSize
            )
        }

        private fun createNear(map: MutableMap<Point, MapTile>, mine: Point, rowNum: Int, colNum: Int) {
            val adjacentPoints = AdjacentPoints.create(mine, rowNum, colNum)
            for (adj in adjacentPoints.points) {
                val nearInfo = map.getOrDefault(adj, MapTile.Blank(0))
                if (nearInfo is MapTile.Blank) map[adj] = nearInfo + 1
            }
        }
    }
}
