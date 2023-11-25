package domain

object RandomMineMap {

    fun newMap(mapInfo: MineMapInfo): List<List<Spot>> {
        val flattenMap = (List(mapInfo.mineCount) { Spot(MineStatus.MINED) } + List(mapInfo.point.y * mapInfo.point.x - mapInfo.mineCount) { Spot(MineStatus.EMPTY) }).shuffled()
        return flattenMap.chunked(mapInfo.point.x)
    }
}
