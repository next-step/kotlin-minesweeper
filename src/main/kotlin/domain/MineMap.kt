package domain

import vo.MineMapInfo

class MineMap(val mapInfo: MineMapInfo) {

    private val map: List<Int> =
        (List(mapInfo.mineCount) { 1 } + List(mapInfo.height * mapInfo.width - mapInfo.mineCount) { 0 })
            .shuffled()

    fun mapByLine(line: Int): List<Int> {
        require(line in 0 until mapInfo.height) { "잘못된 라인입니다." }
        return map.subList(line * mapInfo.width, (line + 1) * mapInfo.width)
    }
}
