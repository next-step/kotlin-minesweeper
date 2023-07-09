package minesweeper.domain

class MineMap(height: Height, val width: Width, mineCount: MineCount) {
    private val size: Int = height.value * width.value
    val map: List<Point>

    init {
        require(size >= mineCount.value) { "지도 크기는 지뢰 개수이상이어야 합니다." }

        val mineIndexes = (0..size)
            .shuffled()
            .take(mineCount.value)

        val mutableList = mutableListOf<Point>()
        (1..height.value).forEach { y ->
            (1..width.value).forEach { x ->
                val index = (y - 1) * width.value + (x - 1)

                mutableList.add(point(mineIndexes.contains(index), x, y))
            }
        }

        map = mutableList.toList()
    }

    private fun point(isMine: Boolean, x: Int, y: Int): Point {
        if (isMine) {
            return MinePoint(x, y)
        }

        return Point(x, y)
    }
}
