package minesweeper.model

class MineSweeperMaker {
    fun getMinePosition(width: Int, height: Int, mineCount: Int): List<Int> {
        val squareCount = width * height
        val minePositions = (START_POSITION..squareCount).shuffled().take(mineCount)

        return minePositions.sorted().toList()
    }

    companion object {
        const val START_POSITION = 1
    }
}
