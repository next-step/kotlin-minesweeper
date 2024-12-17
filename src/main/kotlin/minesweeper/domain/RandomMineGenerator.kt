package minesweeper.domain

interface RandomMineGenerator {
    fun generateMinePositions(
        totalCells: Int,
        mineCount: Int,
    ): Set<Int>
}

class RandomMineGeneratorImpl : RandomMineGenerator {
    override fun generateMinePositions(
        totalCells: Int,
        mineCount: Int,
    ): Set<Int> {
        require(mineCount <= totalCells) { "지뢰 개수는 전체 Cell 개수보다 적거나 같아야 합니다" }
        return (0 until totalCells).shuffled().take(mineCount).toSet()
    }
}
