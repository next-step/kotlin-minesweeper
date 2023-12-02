package minesweeper

data class MineMapInfo(val mapSize: MapSize, private val mineCount: MineCount) {
    constructor(row: LineCount, col: LineCount, mineNum: MineCount) : this(MapSize(row, col), mineNum)

    val rowNumber = mapSize.row.count
    val columnNumber = mapSize.column.count
    val mineNumber = mineCount.count

    private val totalNumber = rowNumber * columnNumber

    init {
        require(mineNumber <= totalNumber) {
            "Mine Count should not be bigger than total map tile"
        }
    }
}
