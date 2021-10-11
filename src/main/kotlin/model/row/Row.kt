package model.row

import model.cell.Cell

interface Row {
    fun get(width: Int): Cell

    data class Fake(
        private val height: Int
    ) : Row {
        override fun get(width: Int): Cell {
            return Cell.Fake(height, width)
        }
    }
}