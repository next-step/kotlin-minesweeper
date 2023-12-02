package minesweeper

object ResultView {
    fun showMap(mineMap: MineMap) {
        println("지뢰찾기 게임 시작")
        for (row in 1..mineMap.mapSize.row.count) {
            printRow(mineMap, row)
        }
    }

    private fun printRow(
        mineMap: MineMap,
        row: Int
    ) {
        for (col in 1..mineMap.mapSize.column.count) {
            when (val info = mineMap.mineMap[Point(row, col)]) {
                is MapTile.Mine -> print("* ")
                is MapTile.Blank -> {
                    print("${info.nearCount} ")
                }

                else -> print("0 ")
            }
        }
        println()
    }
}
