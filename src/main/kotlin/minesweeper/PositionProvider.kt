package minesweeper

interface PositionProvider {
    fun provide(dimensions: Dimensions, mineCount: Int): MutableMap<Position, CellType>
}

class DefaultPositionProvider : PositionProvider {
    override fun provide(dimensions: Dimensions, mineCount: Int): MutableMap<Position, CellType> {
        val allCells = dimensions.allPositions()
            .associateWith { CellType.EMPTY }
            .toMutableMap()

        allCells.keys.shuffled()
            .take(mineCount)
            .forEach { position ->
                allCells[position] = CellType.MINE
            }

        return allCells
    }
}
