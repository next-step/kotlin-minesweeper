package minesweeper.model

import minesweeper.service.CellSelector

class MineMap private constructor(
    val rowSize: Int,
    val columnSize: Int
) {
    private val cellPool: Map<Cell, Cell> = getDefaultCellPool(rowSize, columnSize)

    init {
        require(cellPool.isNotEmpty()) { "지뢰맵의 크기는 0이 될 수 없습니다." }
    }

    fun checkBounds(cell: Cell) =
        cell.x in INIT_INDEX until INIT_INDEX + columnSize &&
            cell.y in INIT_INDEX until INIT_INDEX + rowSize

    private fun plantMine(mine: Cell) {
        require(checkBounds(mine)) { "지뢰 좌표가 지뢰맵의 범위를 넘어갑니다." }
        val nearCells = CellSelector.nearCellsOf(mine)
        nearCells.mapNotNull { cellPool[it] }
            .forEach { it.increaseCount() }
    }

    fun plantMines(mines: Mines) {
        mines.forEach(this::plantMine)
    }

    fun selectRandomMines(mineCount: Int): Mines {
        val shuffledMines = cellPool.keys
            .shuffled()
            .take(mineCount)
            .toSet()
        return Mines(shuffledMines)
    }

    fun getNearCount(cell: Cell): Int {
        require(checkBounds(cell)) { "셀 좌표가 지뢰맵의 범위를 넘어갑니다." }
        return cellPool[cell]!!.nearMineCount
    }

    companion object {
        const val INIT_INDEX = 0
        fun of(height: Int, width: Int): MineMap {
            return MineMap(height, width)
        }
    }
}

fun getDefaultCellPool(rowSize: Int, columnSize: Int) =
    List(rowSize) { y -> List(columnSize) { x -> Cell(x, y) } }
        .flatten()
        .associateBy { Cell(it.x, it.y) }
        .toSortedMap(compareBy({ it.y }, { it.x }))
