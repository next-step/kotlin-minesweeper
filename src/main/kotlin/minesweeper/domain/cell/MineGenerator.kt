package minesweeper.domain.cell

class MineGenerator(
    private val maxCount: Int
) {
    fun getRandomPosition(mineCount: Int): List<Int> {
        validation(mineCount)
        return (0 until maxCount).shuffled().take(mineCount)
    }

    private fun validation(mineCount: Int) {
        require(maxCount >= mineCount) { "Cell의 수보다 지뢰가 많을 수 없다." }
    }
}
