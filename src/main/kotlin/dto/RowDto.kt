package dto

import domain.Row

data class RowDto private constructor(private val cells: List<CellDto>) : List<CellDto> by cells {
    constructor(row: Row) : this(row.map { CellDto(it) })

    fun draw(): String = joinToString(CELL_SEPARATOR) { it.draw() }
    fun mineNumbers(): String = joinToString(CELL_SEPARATOR) { it.mineNumber() }
    fun render(): String = joinToString(CELL_SEPARATOR) { it.render() }
    override fun toString(): String = "${render()}    ${mineNumbers()}"

    companion object {
        const val CELL_SEPARATOR = " "
    }
}
