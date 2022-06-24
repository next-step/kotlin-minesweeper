package minesweeper.domain

interface CellsGenerator {
    fun generate(area: Area, mineCount: MineCount): List<Cell>
}

class DefaultCellsGenerator(private val mineSpawner: MineSpawner = RandomMineSpawner) : CellsGenerator {

    override fun generate(area: Area, mineCount: MineCount): List<Cell> {
        val mineCoordinates = mineSpawner.spawn(area, mineCount)
        return generateCells(area, mineCoordinates)
    }

    private fun generateCells(area: Area, mineCoordinates: Coordinates): List<Cell> {
        val allCoordinates = Coordinates.coordinatesInArea(area)
        val mineCells = generateMineCells(mineCoordinates)
        val blockCells = generateBlockCells(allCoordinates, mineCoordinates)
        return (mineCells + blockCells).sortedBy(Cell::coordinate)
    }

    private fun generateMineCells(mineCoordinates: Coordinates) = mineCoordinates.map { Cell.Mine(it) }

    private fun generateBlockCells(allCoordinates: Coordinates, mineCoordinates: Coordinates): List<Cell.Block> {
        val blockCoordinates = allCoordinates - mineCoordinates
        val blockCells = blockCoordinates.map {
            val aroundMineCount = it.aroundCoordinates().containsCount(mineCoordinates)
            Cell.Block(it, aroundMineCount)
        }
        return blockCells
    }
}
