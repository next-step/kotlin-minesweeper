package minesweeper.model

import minesweeper.service.CellSelector.getSurroundingCells

class MineMap private constructor(
    val rowSize: Int,
    val columnSize: Int
) {
    private val cellPool: List<Cell> =
        List(rowSize) { y -> List(columnSize) { x -> Cell(x, y) } }
            .flatten()
            .sortedWith(compareBy({ it.y }, { it.x }))

    init {
        require(cellPool.isNotEmpty()) { "지뢰 맵의 크기는 0이 될 수 없습니다." }
    }

    fun checkBounds(cell: Cell) =
        cell.x in INIT_INDEX until INIT_INDEX + columnSize &&
            cell.y in INIT_INDEX until INIT_INDEX + rowSize

    fun plantMine(mine: Cell) {
        val surroundings = getSurroundingCells(mine)
        cellPool.filter { surroundings.contains(it) }
            .forEach { it.increaseCount() }
    }

    fun selectRandomMines(mineCount: Int): Mines {
        val shuffledMines = cellPool.shuffled()
            .take(mineCount)
            .toSet()
        return Mines(shuffledMines)
    }

    fun forEach(action: (Cell) -> Unit) {
        cellPool.forEach(action)
    }

    companion object {
        const val INIT_INDEX = 0
        fun of(height: Int, width: Int): MineMap {
            return MineMap(height, width)
        }
    }
}
