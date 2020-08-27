package minesweeper.domain

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Position

class CellManager private constructor(
    private val _cells: MutableSet<Cell> = mutableSetOf()
) {
    val cells: List<Cell>
        get() = _cells.toList()
            .sortedWith(compareBy({ it.position.x }, { it.position.y }))

    val size: Int
        get() = _cells.size

    fun addCell(cell: Cell) {
        _cells.add(cell)
    }

    fun changeCell(cell: Cell) {
        if (_cells.removeIf { it.position == cell.position }) _cells.add(cell)
    }

    fun openAll() {
        cells.forEach(::openCell)
    }

    private fun openCell(cell: Cell) {
        if (cell.isMine().not()) {
            val aroundMineCount = cell.getAroundPositions().sumBy(::getMineCounter)
            changeCell(cell.open(aroundMineCount))
        }
    }

    private fun getMineCounter(position: Position) =
        if (getCell(position)?.isMine() == true) 1 else 0

    private fun getCell(position: Position) =
        cells.find { it.isPosition(position) }

    companion object {
        fun newInstance() = CellManager()
        fun newInstance(cells: Set<Cell>) = CellManager(cells.toMutableSet())
    }
}
