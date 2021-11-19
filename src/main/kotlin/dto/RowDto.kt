package dto

import domain.Row

class RowDto private constructor(private val cells: List<CellDto>) : List<CellDto> by cells {
    constructor(row: Row) : this(row.map { CellDto(it) })
}
