package minesweeper.domain

class MineSweeperApp(
    height: Int,
    width: Int
) {
    private val mineMap = MineMap(height, width)

    fun initMine(mineCount: Int) {
        mineMap.setMines(mineCount)
    }

    fun getMaxMineCount() = mineMap.getMapSize()

    fun getResult() = mineMap
}
