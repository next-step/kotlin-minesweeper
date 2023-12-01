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
                emptyMap(mineMapInfo.mapSize).apply {
                    for (mine in mineList.mineList) {
                        this[mine] = MapTile.Mine
                    }

                    for (mine in mineList.mineList) {
                        createNear(this, mine, mineMapInfo.mapSize)
                    }
                },
                mineMapInfo.mapSize
            )
        }

        private fun emptyMap(mapSize: MapSize): MutableMap<Point, MapTile> {
            return mutableMapOf<Point, MapTile>().apply {
                for (i in 0 until mapSize.row.count) {
                    for (j in 0 until mapSize.column.count) {
                        put(Point(i, j), MapTile.Blank(0))
                    }
                }
            }
        }

        private fun createNear(map: MutableMap<Point, MapTile>, mine: Point, mapSize: MapSize) {
            val adjacentPoints = mine.getAdjacentPoint(mapSize)
            for (adj in adjacentPoints) {
                val nearInfo = map[adj]
                if (nearInfo is MapTile.Blank) map[adj] = nearInfo + 1
            }
        }
    }
}
