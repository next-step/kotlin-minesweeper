package minesweeper

interface CellProvider {
    fun provide(dimensions: Dimensions): List<Cell>
}

class DefaultCellProvider : CellProvider {
    override fun provide(dimensions: Dimensions): List<Cell> {
        val cells = dimensions.createCells()
        cells.shuffled()
            .take(dimensions.mineCount)
            .forEach { cell ->
                cell.changeMine()
            }

        return cells
    }
}
