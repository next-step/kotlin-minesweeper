package minesweeper

data class MineMapInfo(val mapSize: MapSize, private val mineNum: MineCount) {
    constructor(row: LineCount, col: LineCount, mineNum: MineCount) : this(MapSize(row, col), mineNum)

    val rowCnt = mapSize.row.count
    val colCnt = mapSize.column.count
    val mineCnt = mineNum.count

    val total = rowCnt * colCnt

    init {
        require(mineCnt <= total) {
            "Mine Count should not be bigger than total map tile"
        }
    }
}
