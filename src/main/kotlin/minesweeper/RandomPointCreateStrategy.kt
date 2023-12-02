package minesweeper

class RandomPointCreateStrategy : MinePointCreateStrategy {
    override fun createMinePoints(mineMapInfo: MineMapInfo): List<Point> {
        val tileNum = mineMapInfo.rowNumber * mineMapInfo.columnNumber - 1
        return (0..tileNum).toList()
            .shuffled()
            .take(mineMapInfo.mineNumber)
            .map { it.toPoint(mineMapInfo.rowNumber) }
    }

    private fun Int.toPoint(rowNum: Int): Point {
        val rowIdx = this / rowNum
        val colIdx = this % rowNum
        return Point(rowIdx + 1, colIdx + 1)
    }
}
