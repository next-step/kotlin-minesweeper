package minesweeper.domain

class ManualMinePlacementStrategy(private val mineIndices: List<Int>) : MinePlacementStrategy {
    override fun selectIndices(allCells: MutableList<Cell>, mineCount: Int): List<Int> {
        return mineIndices.take(mineCount)
    }
}
