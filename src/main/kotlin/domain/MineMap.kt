package domain

class MineMap(private val map: ArrayMap) {

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

    fun resultMineStatus(y: Int, x: Int): String {
        val nearMineCount = nearMineCount(y, x)
        return map.getPoint(y, x).spotSymbol(nearMineCount)
    }

    fun getHeight(): Int = map.height

    fun getWidth(): Int = map.width

    private fun nearMineCount(y: Int, x: Int): Int =
        delta.map {
            map.getPointOrNull(y + it.y, x + it.x)
        }.count {
            it != null && it.isMine()
        }
}
