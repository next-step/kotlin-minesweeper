package minesweeper

object ResultView {
    fun showMap(mineMap: MineMap) {
        println("지뢰찾기 게임 시작")
        val map = mineMap.mineMap
        for (row in 0 until mineMap.mineMapInfo.rowCnt) {
            printRow(mineMap, map, row)
        }
    }

    private fun printRow(
        mineMap: MineMap,
        map: List<List<Mine>>,
        row: Int
    ) {
        for (col in 0 until mineMap.mineMapInfo.colCnt) {
            when (map[row][col]) {
                Mine.MINE -> print("* ")
                Mine.BLANK -> print("C ")
            }
        }
        println()
    }
}
