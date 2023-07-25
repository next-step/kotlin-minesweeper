package minesweeper.ui

import minesweeper.domain.board.Board
import minesweeper.domain.field.Mark

class OutputView {
    fun printStart(board: Board) {
        println("지뢰찾기 게임 시작")
        printBoard(board)
    }

    private fun printBoard(board: Board) {
        groupByLine(board).forEach { (_, marks) ->
            println(marks.joinToString(" ") { toSymbol(it) })
        }
    }

    private fun groupByLine(board: Board): Map<Int, List<Mark>> {
        return board.markMap.entries.groupBy({ it.key.x }, { it.value })
    }

    private fun toSymbol(mark: Mark): String {
        if (mark.isMine()) {
            return "*"
        }
        return "C"
    }
}
