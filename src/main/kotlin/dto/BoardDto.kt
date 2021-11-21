package dto

import domain.Board

class BoardDto private constructor(private val rows: List<RowDto>) : List<RowDto> by rows {
    constructor(board: Board) : this(board.map { RowDto(it) })
}
