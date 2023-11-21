package domain

import vo.MineMapInfo
import vo.Spot

class MineMap(val mapInfo: MineMapInfo) {

    private val map: List<Spot> =
        (List(mapInfo.mineCount) { Spot(true) } + List(mapInfo.height * mapInfo.width - mapInfo.mineCount) { Spot(false) })
            .shuffled()

    fun isMineOn(x: Int, y: Int): String {
        require(x in 0 until mapInfo.width) { "잘못된 x값입니다." }
        require(y in 0 until mapInfo.height) { "잘못된 y값입니다." }
        return map[y * mapInfo.width + x].isMineOn()
    }
}
