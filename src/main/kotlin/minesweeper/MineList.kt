package minesweeper

class MineList(val mineList: List<Point>) {
    companion object {
        fun createMineList(mineMapInfo: MineMapInfo, createStrategy: MinePointCreateStrategy): MineList {
            return MineList(createStrategy.createMinePoints(mineMapInfo))
        }
    }
}
