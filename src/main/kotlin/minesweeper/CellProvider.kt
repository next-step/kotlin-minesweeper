package minesweeper

interface CellProvider {
    fun provide(dimensions: Dimensions): Cells
}

class DefaultCellProvider(private val mineCount: Int) : CellProvider {
    private fun validateMineCount(dimensions: Dimensions, mineCount: Int) {
        require(mineCount > 0) { "지뢰 갯수는 0보다 커야 합니다." }
        require(mineCount < dimensions.width * dimensions.height) { "지뢰 갯수는 최대치 보다 작아야 합니다" }
    }

    override fun provide(dimensions: Dimensions): Cells {
        validateMineCount(dimensions, mineCount)
        val cells: Cells = dimensions.createDefaultCells()
        val allPositions = dimensions.allPositions()

        return cells.assignMinesToCells(
            allPositions
                .shuffled()
                .take(mineCount)
        )
    }
}
