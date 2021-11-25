package dto

import domain.Row

data class RowDto(private val cells: List<CellDto>) : List<CellDto> by cells {
    constructor(row: Row) : this(row.map { CellDto(it) })

    override fun toString(): String = joinToString(CELL_SEPARATOR)

    companion object {
        private const val CELL_SEPARATOR = " "
    }
}
