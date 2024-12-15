package minesweeper

interface PositionProvider {
    fun provide(dimensions: Dimensions, mineCount: Int): MutableMap<Position, CellType>
}

class DefaultPositionProvider : PositionProvider {
    override fun provide(dimensions: Dimensions, mineCount: Int): MutableMap<Position, CellType> {
        return dimensions.allPositions()
            .shuffled()
            .take(mineCount)
            .associateWith { CellType.MINE }
            .toMutableMap()
    }
}
