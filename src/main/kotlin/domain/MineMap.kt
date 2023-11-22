package domain

import vo.Spot

/**
 * 지뢰찾기 맵은 이중 배열의 형태
 * 첫 번째 (outer) List는 y축
 * 두 번째 (inner) List는 x축
 */
class MineMap(private val map: List<List<Spot>>) {

    fun isMineOn(x: Int, y: Int): String {
        require(y in map.indices) { "잘못된 x값입니다." }
        require(x in map.first().indices) { "잘못된 y값입니다." }
        return map[y][x].isMineOn()
    }

    fun getHeight(): Int = map.size

    fun getWidth(): Int = map.first().size
}
