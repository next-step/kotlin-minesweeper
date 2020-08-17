package minesweeper.domain

class MineMap(private val mapArea: Pair<Int, Int>, _mines: Mines) {
    var mines = _mines
        private set

    init {
        validateMineCount()
    }

    constructor(area: Pair<Int, Int>, mineCount: Int) : this(
        area,
        _mines = Mines((1..mineCount).map { Mine.createMine(area, randomPositionStrategy) })
    )

    private fun validateMineCount() {
        while (mines.hasDuplicate()) {
            val duplicateCount = mines.size() - mines.duplicateRemoved().size
            val oldMines = mines.duplicateRemoved()
            val newMines = (1..duplicateCount).map { Mine.createMine(mapArea, randomPositionStrategy) }
            mines = Mines(oldMines + newMines)
        }
    }

    fun stateOfMap(): List<List<String>> {
        return mines.setIntoMap(mapInitialized(mapArea.first, mapArea.second))
    }

    companion object {
        private const val MAP_SYMBOL = "C"
        private val randomPositionStrategy = RandomPositionStrategy

        fun mapInitialized(height: Int, width: Int): List<List<String>> {
            return List(height) { List(width) { MAP_SYMBOL } }
        }
    }
}
