package minesweeper.domain

class MineMap(height: Height, width: Width, mineCount: MineCount) {
    private val size: Int = height.value * width.value
    val map: List<Point>

    init {
        require(size >= mineCount.value) { "지도 크기는 지뢰 개수이상이어야 합니다." }

        val mutableList = mutableListOf<Point>()
        (1..height.value).forEach { y ->
            (1..width.value).forEach {  x ->
                mutableList.add(Point(x, y))
            }
        }

        map = mutableList.toList()
    }
}