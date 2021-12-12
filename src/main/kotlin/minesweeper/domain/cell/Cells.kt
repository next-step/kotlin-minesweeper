package minesweeper.domain.cell

import minesweeper.domain.position.Position

class Cells(cells: Map<Position, Cell>) {

    private val _cells: MutableMap<Position, Cell> = cells.toMutableMap()
    val cells get() = _cells.toMap()

    fun isMine(position: Position): Boolean {
        val cell = _cells[position] ?: return false
        return cell.isMine
    }

    fun open(position: Position) {
        val cell = _cells[position] ?: return
        if (cell.isOpen) return

        open(cell, position)

        if (cell is BlockCell && cell.hasNoMineAround) {
            openAround(position)
        }
    }

    private fun open(cell: Cell, position: Position) {
        val openCell = cell.open()
        _cells.replace(position, openCell)
    }

    private fun openAround(position: Position) {
        position.around().positions.forEach(::open)
    }

    fun isAllOpen(): Boolean {
        return _cells.values
            .filter(Cell::isNotMine)
            .all(Cell::isOpen)
    }
}

private val Cell.isNotMine get() = !isMine

