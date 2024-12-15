package minesweeper

interface PositionProvider {
    fun provide(dimensions: Dimensions): MutableMap<Position, CellType>
}

class DefaultPositionProvider : PositionProvider {
    override fun provide(dimensions: Dimensions): MutableMap<Position, CellType> {
        val allCells = dimensions.allPositions()
            .associateWith { CellType.EMPTY }
            .toMutableMap()

        allCells.keys.shuffled()
            .take(dimensions.mineCount)
            .forEach { position ->
                allCells[position] = CellType.MINE
            }

        return allCells
    }
}
