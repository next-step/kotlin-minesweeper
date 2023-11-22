package domain

import vo.MineMapInfo
import vo.MineStatus
import vo.Spot

object MineMapGenerator {

    fun newMap(mapInfo: MineMapInfo): List<List<Spot>> {
        val flattenMap = (List(mapInfo.mineCount) { Spot(MineStatus.MINED) } + List(mapInfo.height * mapInfo.width - mapInfo.mineCount) { Spot(MineStatus.EMPTY) }).shuffled()
        return flattenMap.chunked(mapInfo.width)
    }
}
