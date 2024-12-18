package minesweeper

interface CellProvider {
    fun provide(dimensions: Dimensions): Cells
}

class DefaultCellProvider : CellProvider {
    override fun provide(dimensions: Dimensions): Cells {
        val cells: Cells = dimensions.createDefaultCells()
        val mineCount = dimensions.mineCount
        val allPositions = dimensions.allPositions()

        return cells.assignMinesToCells(
            allPositions
                .shuffled()
                .take(mineCount)
        )
    }
}
