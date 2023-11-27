package minesweeper

class MineMap(
    val mineMapInfo: MineMapInfo,
    createStrategy: MinePointCreateStrategy = RandomPointCreateStrategy()
) {
    private val mineList: MineList =
        MineList.createMineList(mineMapInfo, createStrategy)
    val mineMap: List<List<Mine>> =
        MutableList(mineMapInfo.rowCnt) { MutableList(mineMapInfo.colCnt) { Mine.BLANK } }.apply {
            for (mine in mineList.mineList) {
                this[mine.row][mine.col] = Mine.MINE
            }
        }
}

enum class Mine {
    BLANK, MINE
}
