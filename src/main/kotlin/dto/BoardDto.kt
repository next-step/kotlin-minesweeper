package dto

import domain.Board

data class BoardDto private constructor(private val rows: List<RowDto>) : List<RowDto> by rows {
    constructor(board: Board) : this(board.map { RowDto(it) })

    fun draw(): String = joinToString(ROW_SEPARATOR) { it.draw() }
    fun mineNumbers(): String = joinToString(ROW_SEPARATOR) { it.mineNumbers() }
    fun render(): String = joinToString(ROW_SEPARATOR) { it.render() }
    override fun toString(): String = joinToString(ROW_SEPARATOR)

    companion object {
        const val ROW_SEPARATOR = "\n"
    }
}
