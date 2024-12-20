package mine.domain

data class Mine(val height: Int, val width: Int, val mineCount: Int) {
    val minesweeper: List<MineRow>

    init {
        require(height > MINE_MIN_VALUE) { "높이는 0보다 커야합니다." }
        require(width > MINE_MIN_VALUE) { "너비는 0보다 커야합니다." }
        require(mineCount > MINE_MIN_VALUE) { "지뢰 개수는 0보다 커야합니다." }
        require(mineCount <= height * width) { "지뢰 개수는 전체 칸의 수보다 많을 수 없습니다." }
        minesweeper = MineRandomPlacer().placeMines(height, width, mineCount)
//        minesweeper =
    }

    fun calculateNorMalCellValues(): List<MineRow> {
        return BoardCalculator().calculateBoard(minesweeper, width, height)
    }

    companion object {
        const val MINE_MIN_VALUE = 0
    }
}
