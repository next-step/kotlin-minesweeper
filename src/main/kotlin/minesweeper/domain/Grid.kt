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
                if (index in minePositions) Cell(Mine()) else Cell(Empty())
            }
        }
    }
}