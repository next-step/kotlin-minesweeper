package dto

import domain.Blank
import domain.Board
import domain.Cell
import domain.Mine

data class BoardDto(
    val cells: List<String>
) {
    companion object {
        fun from(board: Board): BoardDto {
            return BoardDto(
                board.cells.map {
                    toString(it)
                }
            )
        }

        private fun toString(cell: Cell): String {
            return when (cell) {
                is Mine -> "*"
                is Blank -> "C"
            }
        }
    }
}
