package dto

import domain.Board

data class BoardDto(private val rows: List<RowDto>) : List<RowDto> by rows {
    constructor(board: Board) : this(board.map { RowDto(it) })

    override fun toString(): String = joinToString(ROW_SEPARATOR)

    companion object {
        private const val ROW_SEPARATOR = "\n"
    }
}
