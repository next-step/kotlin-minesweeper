package dto

import domain.Row

data class RowDto(private val cells: List<CellDto>) : List<CellDto> by cells {
    constructor(row: Row) : this(row.map { CellDto.from(it) })

    override fun toString(): String =
        "${joinToString(CELL_SEPARATOR) { it.covered }}    ${joinToString(CELL_SEPARATOR) { it.uncovered }}"

    companion object {
        private const val CELL_SEPARATOR = " "
    }
}
