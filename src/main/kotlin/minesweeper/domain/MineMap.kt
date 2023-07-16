package minesweeper.domain

class MineMap(private val mineMapSize: MineMapSize, mineIndexes: MineIndexes) {
    private val size: Int = mineMapSize.size()
    val map: List<Point>

    init {
        require(size >= mineIndexes.size()) { "지도 크기는 지뢰 개수이상이어야 합니다." }

        val height = mineMapSize.height()
        val width = mineMapSize.width()

        map = points(height, width, mineIndexes)
    }

    fun width(): Int {
        return mineMapSize.width()
    }

    fun getPoint(index: Int): Point {
        return map[index]
    }

    private fun points(
        height: Int,
        width: Int,
        mineIndexes: MineIndexes
    ): List<Point> {
        return (1..height).flatMap { y ->
            generateRow(width, mineIndexes, y)
        }
    }

    private fun generateRow(
        width: Int,
        mineIndexes: MineIndexes,
        y: Int
    ) = (1..width).map { x ->
        point(mineIndexes.contains(mineMapSize.getIndex(y, x)), x, y)
    }

    private fun point(isMine: Boolean, x: Int, y: Int): Point {
        if (isMine) {
            return MinePoint(x, y)
        }

        return Point(x, y)
    }
}
