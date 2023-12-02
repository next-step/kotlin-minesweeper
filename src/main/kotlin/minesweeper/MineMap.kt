package minesweeper

class MineMap(
    val mineMap: Map<Point, MapTile>,
    val mapInfo: MineMapInfo
) {
    val totalSize = mapInfo.totalNumber

    companion object {
        fun create(
            mineMapInfo: MineMapInfo,
            createStrategy: MinePointCreateStrategy = RandomPointCreateStrategy()
        ): MineMap {
            val mineList: MineList =
                MineList.createMineList(mineMapInfo, createStrategy)

            return MineMap(
                emptyMap(mineMapInfo.mapSize).apply {
                    for (mine in mineList.mineList) {
                        this[mine] = MapTile.Mine
                    }

                    for (mine in mineList.mineList) {
                        createNear(this, mine, mineMapInfo.mapSize)
                    }
                },
                mineMapInfo
            )
        }

        private fun emptyMap(mapSize: MapSize): MutableMap<Point, MapTile> {
            return mutableMapOf<Point, MapTile>().apply {
                for (i in 1..mapSize.row.count) {
                    for (j in 1..mapSize.column.count) {
                        put(Point(i, j), MapTile.Blank(0))
                    }
                }
            }
        }

        private fun createNear(map: MutableMap<Point, MapTile>, mine: Point, mapSize: MapSize) {
            val adjacentPoints = mine.getAdjacentPoints(mapSize)
            for (adj in adjacentPoints) {
                val nearInfo = map[adj]
                if (nearInfo is MapTile.Blank) map[adj] = nearInfo + 1
            }
        }
    }
}
