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
        val cell = replaceIfNotOpen(position, Cell::open) ?: return

        if (cell is BlockCell && cell.hasNoMineAround) {
            openAround(position)
        }
    }

    fun flag(position: Position) {
        replaceIfNotOpen(position, Cell::flag)
    }

    fun isAllOpen(): Boolean {
        return _cells.values
            .filter(Cell::isNotMine)
            .all(Cell::isOpen)
    }

    private fun replaceIfNotOpen(position: Position, transform: Cell.() -> Cell): Cell? {
        val cell = _cells[position] ?: return null
        if (cell.isOpen) return null

        return transform(cell).also {
            _cells.replace(position, it)
        }
    }

    private fun openAround(position: Position) {
        position.around().positions.forEach(::open)
    }
}

private val Cell.isNotMine get() = !isMine

