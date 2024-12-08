package minesweeper.domain

interface MinePlacementStrategy {
    fun placeMines(height: Int, width: Int, mineCount: Int): List<Int>
}

class RandomMinePlacementStrategy : MinePlacementStrategy {
    override fun placeMines(height: Int, width: Int, mineCount: Int): List<Int> {
        return (0 until height * width).shuffled()
            .take(mineCount)
    }
}
