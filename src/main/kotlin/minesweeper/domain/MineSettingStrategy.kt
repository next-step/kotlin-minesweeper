package minesweeper.domain

interface MineSettingStrategy {
    fun getLocations(wholeBlockCount: Int, mineCount: Int): List<Int>
}

class RandomMineSettingStrategy : MineSettingStrategy {
    override fun getLocations(wholeBlockCount: Int, mineCount: Int): List<Int> {
        return (0 until wholeBlockCount).shuffled().take(mineCount)
    }
}
