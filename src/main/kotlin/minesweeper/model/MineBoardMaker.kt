package minesweeper.model

const val START_POSITION = 0

class MineBoardMaker {
    fun setRandomMinePosition(mineCount: Int, totalCount: Int): List<Int> {
        return (START_POSITION until totalCount).shuffled()
                .take(mineCount)
                .sorted()
    }
}
