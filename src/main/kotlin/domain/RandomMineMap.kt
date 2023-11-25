package domain

object RandomMineMap {

    fun newMap(mapInfo: MineMapInfo): List<List<Spot>> {
        val shuffledFlattenMap = flattenMineMap(mapInfo).shuffled()
        return shuffledFlattenMap.chunked(mapInfo.point.x)
    }

    private fun flattenMineMap(mapInfo: MineMapInfo): List<Spot> =
        initializeList(mapInfo.mineCount, MineStatus.MINED) +
            initializeList(mapInfo.point.y * mapInfo.point.x - mapInfo.mineCount, MineStatus.EMPTY)

    private fun initializeList(size: Int, initValue: MineStatus): List<Spot> =
        List(size) { Spot(initValue) }
}
