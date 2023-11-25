package domain

/**
 * 지뢰찾기 맵은 이중 배열의 형태
 * 첫 번째 (outer) List는 y축
 * 두 번째 (inner) List는 x축
 */
class MineMap(private val map: List<List<Spot>>) {

    private val delta = listOf(
        Point(-1, -1),
        Point(0, -1),
        Point(1, -1),
        Point(-1, 0),
        Point(1, 0),
        Point(-1, 1),
        Point(0, 1),
        Point(1, 1)
    )

    fun resultMineStatus(x: Int, y: Int): String {
        require(y in map.indices) { "잘못된 x값입니다." }
        require(x in map.first().indices) { "잘못된 y값입니다." }
        val nearMineCount = nearMineCount(x, y)
        return map[y][x].spotSymbol(nearMineCount)
    }

    fun getHeight(): Int = map.size

    fun getWidth(): Int = map.first().size

    private fun nearMineCount(x: Int, y: Int): Int =
        delta.map {
            map.getOrNull(y + it.y)
                ?.getOrNull(x + it.x)
        }.count {
            it != null && it.isMine()
        }
}
