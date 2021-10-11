package model.row

import model.cell.Cell

data class ListRow(
    private val cells: List<Cell>
) : Row {
    override fun get(width: Int): Cell {
        return cells[width]
    }
}