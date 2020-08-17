package minesweeper.domain

import minesweeper.domain.cell.Cell

class CellManager(
    private val _cells: MutableSet<Cell> = mutableSetOf()
) {
    val cells: List<Cell>
        get() = _cells.toList()
            .sortedWith(compareBy({ it.position.x }, { it.position.y }))

    fun addCell(cell: Cell) {
        _cells.add(cell)
    }

    fun changeCell(cell: Cell) {
        if (_cells.removeIf { it.position == cell.position }) _cells.add(cell)
    }

    fun getSize() = cells.size
}
