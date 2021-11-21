package view

import dto.BoardDto
import dto.RowDto

object OutputView {
    fun printStart() = println("\n지뢰찾기 게임 시작")

    fun printBoard(board: BoardDto) {
        board.forEach { printRow(it) }
    }

    private fun printRow(row: RowDto) = println("${row.joinToString(CELL_SEPARATOR)}")
    private const val CELL_SEPARATOR = " "
}
