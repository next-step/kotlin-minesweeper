package minesweeper.domain

class Grid(
    private val dimension: Dimension,
    private val mineCount: MineCount
) {
    private val cells: List<List<Cell>> = initializeCells()

    private fun initializeCells(): List<List<Cell>> {
        val totalCells = dimension.totalCells()
        val minePositions = RandomMineGenerator.generateMinePositions(totalCells, mineCount.getCount())
        return (0 until dimension.height).map { row ->
            (0 until dimension.width).map { col ->
                val index = row * dimension.width + col
                if (minePositions.contains(index)) Cell(Mine()) else Cell(Empty())
            }
        }
    }

    fun getCells(): List<List<Cell>> = cells
}