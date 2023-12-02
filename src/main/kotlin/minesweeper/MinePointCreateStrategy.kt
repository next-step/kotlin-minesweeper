package minesweeper

interface MinePointCreateStrategy {
    fun createMinePoints(mineMapInfo: MineMapInfo): List<Point>
}
