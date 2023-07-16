package minesweeper.domain

class MineMap(private val mineMapSize: MineMapSize, mineCount: MineCount) {
    private val size: Int = mineMapSize.size()
    val map: List<Point>

    init {
        require(size >= mineCount.value) { "지도 크기는 지뢰 개수이상이어야 합니다." }

        val mineIndexes = (0..size)
            .shuffled()
            .take(mineCount.value)
        val height = mineMapSize.height()
        val width = mineMapSize.width()

        map = points(height, width, mineIndexes)
    }

    private fun points(
        height: Int,
        width: Int,
        mineIndexes: List<Int>
    ): List<Point> {
        return (1..height).flatMap { y ->
            generateRow(width, mineIndexes, y)
        }
    }

    private fun generateRow(
        width: Int,
        mineIndexes: List<Int>,
        y: Int
    ) = (1..width).map { x ->
        point(mineIndexes.contains(mineMapSize.getIndex(y, x)), x, y)
    }

    fun width(): Int {
        return mineMapSize.width()
    }

    private fun point(isMine: Boolean, x: Int, y: Int): Point {
        if (isMine) {
            return MinePoint(x, y)
        }

        return Point(x, y)
    }
}
