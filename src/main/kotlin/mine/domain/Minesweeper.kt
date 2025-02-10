package mine.domain

import mine.dto.Coordinate

data class Minesweeper(
    val height: Int,
    val width: Int,
    val mineCount: Int,
    val mineBoard: MineBoard = createMineBoard(height, width, mineCount),
) {
    init {
        require(height > HEIGHT_MIN_VALUE) { "높이는 1 이상이어야 합니다." }
        require(width > WIDTH_MIN_VALUE) { "너비는 1 이상이어야 합니다." }
        require(mineCount in MINE_MIN_VALUE..(height * width)) { "지뢰 개수는 1개이상 이며 보드 크기를 초과할수 없습니다." }
    }

    fun areAllSafeCellsOpened(): Boolean {
        return mineBoard.areAllSafeCellsOpened()
    }

    fun openCells(coordinate: Coordinate) {
        mineBoard.openCells(coordinate)
    }

    companion object {
        const val MINE_MIN_VALUE = 1
        const val HEIGHT_MIN_VALUE = 0
        const val WIDTH_MIN_VALUE = 0

        private fun createMineBoard(
            height: Int,
            width: Int,
            mineCount: Int,
        ): MineBoard {
            val placer = MineRandomPlacer()
            val rawBoard = placer.placeMines(height, width, mineCount)
            return BoardCalculator().calculateBoard(MineBoard(rawBoard))
        }
    }
}
