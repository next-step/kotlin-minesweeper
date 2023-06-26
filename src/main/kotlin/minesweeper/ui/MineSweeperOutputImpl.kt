package minesweeper.ui

import minesweeper.domain.Line
import minesweeper.domain.MineBoard

private const val BOARD_DELIMITER = " "

object MineSweeperOutputImpl : MineSweeperOutput() {
    override fun printMineBoard(mineBoard: MineBoard) {
        buildString {
            append("\n지뢰찾기 게임 시작\n")
            mineBoard.lines.forEach {
                append("${convertToString(it)}\n")
            }
        }.run(::println)
    }

    private fun convertToString(line: Line): String =
        line.joinToString(separator = BOARD_DELIMITER) { convertToSymbol(it) }
}
