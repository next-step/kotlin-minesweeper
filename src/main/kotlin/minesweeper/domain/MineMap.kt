package minesweeper.domain

class MineMap(
    private val dimension: MapDimension,
    _mines: Mines
) {
    var mines = _mines
        private set

    init {
        validateMineCount()
    }

    constructor(height: Int, width: Int, mineCount: Int) : this(
        MapDimension(height, width),
        _mines = Mines((1..mineCount).map { Mine(dimensionBounds = MapDimension(height, width)) })
    )

    private fun validateMineCount() {
        while (mines.hasDuplicate()) {
            val nonDuplicateMines = mines.duplicateRemoved()
            val duplicateCount = mines.size() - nonDuplicateMines.size
            val newMines = (1..duplicateCount).map { Mine(dimensionBounds = dimension) }
            mines = Mines(nonDuplicateMines + newMines)
        }
    }

    fun stateOfMap(): List<List<String>> {
        return mines.setIntoMap(mapInitialized(dimension.height, dimension.width))
    }

    companion object {
        private const val MAP_SYMBOL = "C"

        fun mapInitialized(height: Height, width: Width): List<List<String>> {
            return List(height.value) { List(width.value) { MAP_SYMBOL } }
        }
    }
}
