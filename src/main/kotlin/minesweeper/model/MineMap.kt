package minesweeper.model

import minesweeper.service.CellSelector.getSurroundingCells

class MineMap private constructor(
    private val value: List<List<Cell>>
) {
    init {
        require(value.isNotEmpty()) { "행의 크기는 0이 될 수 없습니다." }
        require(value.first().isNotEmpty()) { "열의 크기는 0이 될 수 없습니다." }
    }

    val rowSize = value.size
    val columnSize = value.first().size

    fun checkBounds(cell: Cell) =
        cell.x in INIT_INDEX until INIT_INDEX + columnSize &&
            cell.y in INIT_INDEX until INIT_INDEX + rowSize

    fun increaseSurroundingCount(mine: Cell) {
        val surroundings = getSurroundingCells(mine)
        value.flatten() // 밸류 자체를 flatten()으로 만드는게 나을듯?
            .filter { surroundings.contains(it) }
            .forEach { it.increaseCount() }
    }

    fun selectRandomMines(mineCount: Int): Mines {
        val shuffledMines = value.flatten()
            .shuffled()
            .take(mineCount)
            .toSet()
        return Mines(shuffledMines)
    }

    fun forEach(action: (List<Cell>) -> Unit) {
        value.forEach(action)
    }

    companion object {
        private const val INIT_INDEX = 0
        fun of(height: Int, width: Int): MineMap {
            return MineMap(List(height) { y -> List(width) { x -> Cell(x, y) } })
        }
    }
}
