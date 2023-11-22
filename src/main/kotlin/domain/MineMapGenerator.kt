package domain

import vo.MineMapInfo
import vo.Spot

object MineMapGenerator {

    fun newMap(mapInfo: MineMapInfo): List<List<Spot>> {
        val flattenMap = (List(mapInfo.mineCount) { Spot(true) } + List(mapInfo.height * mapInfo.width - mapInfo.mineCount) { Spot(false) }).shuffled()
        return flattenMap.chunked(mapInfo.width)
    }
}
