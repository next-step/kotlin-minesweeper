package minesweeper

class RandomPointCreateStrategy : MinePointCreateStrategy {
    override fun createMinePoints(mineMapInfo: MineMapInfo): List<Point> {
        val tileNum = mineMapInfo.rowNumber * mineMapInfo.columnNumber - 1
        return (0..tileNum).toList()
            .shuffled()
            .take(mineMapInfo.mineNumber)
            .map { it.toPoint(mineMapInfo.rowNumber) }
    }
}
