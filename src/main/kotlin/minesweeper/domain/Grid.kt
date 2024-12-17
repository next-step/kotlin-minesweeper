package minesweeper.domain

class Grid(
    private val dimension: Dimension,
    private val mineCount: MineCount,
    private val randomMineGenerator: RandomMineGenerator = RandomMineGeneratorImpl()
) {
    val cells: List<List<Cell>> = initializeCells()

    private fun initializeCells(): List<List<Cell>> {
        val totalCells = dimension.totalCells()
        val minePositions = randomMineGenerator.generateMinePositions(totalCells, mineCount.count)
        return (0 until dimension.height).map { row ->
            (0 until dimension.width).map { col ->
                val index = row * dimension.width + col
                createCell(index in minePositions)
            }
        }
    }

    private fun createCell(isIndexMine: Boolean): Cell {
        return if (isIndexMine) Cell(Mine()) else Cell(Empty())
    }
}