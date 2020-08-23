package minesweeper.domain

import minesweeper.domain.cell.Cell

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

    companion object {
        fun newInstance() = CellManager()
        fun newInstance(cells: Set<Cell>) = CellManager(cells.toMutableSet())
    }
}
