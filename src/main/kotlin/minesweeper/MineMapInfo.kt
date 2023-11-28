package minesweeper

data class MineMapInfo(private val rowNum: LineCount, private val colNum: LineCount, private val mineNum: MineCount) {
    val rowCnt = rowNum.count
    val colCnt = colNum.count
    val mineCnt = mineNum.count

    val total = rowCnt * colCnt

    init {
        require(mineCnt <= total) {
            "Mine Count should not be bigger than total map tile"
        }
    }
}
