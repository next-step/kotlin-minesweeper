package minesweeper.domain

class BoardFactory(private val randomGenerator: RandomGenerator) {

    fun createBy(height: Height, width: Width, mineCount: MineCount): Board {
        val mineIndexes = getRandomIndexes(height, width, mineCount)
        val positions = Positions.from(height, width)
        val mineMap = MineMap.from(positions, mineIndexes)
        return mineMap.getBoards()
    }

    private fun getRandomIndexes(height: Height, width: Width, mineCount: MineCount): List<Int> {
        val cellCount = height.value * width.value
        return randomGenerator.generate(until = cellCount, count = mineCount.value)
    }
}
