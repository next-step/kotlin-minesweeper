package view

import dto.BoardDto
import dto.RowDto

object OutputView {
    fun printStart() = println("\n지뢰찾기 게임 시작")
    fun printResult(win: Boolean) = println("\n${if (win) "Win Game." else "Lose Game."}")

    fun printBoard(board: BoardDto) {
        board.forEach { printRow(it) }
    }

    private fun printRow(row: RowDto) =
        println("${row.joinToString(CELL_SEPARATOR)} $BOARD_SEPARATOR ${row.joinToString(CELL_SEPARATOR) { it.mineNumber() }}")

    private const val CELL_SEPARATOR = " "
    private const val BOARD_SEPARATOR = "        "
}
