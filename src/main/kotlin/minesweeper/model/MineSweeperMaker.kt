package minesweeper.model

class MineSweeperMaker {
    fun getMinePosition(width: Int, height: Int, mineCount: Int): List<Int> {
        val squareCount = width * height
        val minePositionCandidate = (START_POSITION..squareCount).toMutableList()
        val minePositions = mutableListOf<Int>()

        while (minePositions.size < mineCount) {
            val random = minePositionCandidate.random()

            minePositions.add(random)
            minePositionCandidate.remove(random)
        }

        return minePositions.sorted()
    }

    companion object {
        const val START_POSITION = 1
    }
}
