package minesweeper

class RandomPointCreateStrategy : MinePointCreateStrategy {
    override fun createMinePoints(mineMapInfo: MineMapInfo): List<Point> {
        val tileNum = mineMapInfo.rowCnt * mineMapInfo.colCnt - 1
        return (0..tileNum).toList().shuffled().take(mineMapInfo.mineCnt).map { it.getPoint(mineMapInfo.rowCnt) }
    }
}
