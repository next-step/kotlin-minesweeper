package minesweeper.presentation

import minesweeper.domain.Board
import minesweeper.domain.Cell

object UI {

    fun drawStartMessage() {
        println("지뢰찾기 게임 시작")
    }

    fun drawBoard(board: Board) {
        board.groupByRow().forEach { (_, row) ->
            println(row.joinToString(" ") { convertCellMark(it) })
        }
    }

    private fun convertCellMark(cell: Cell): String {
        return when (cell) {
            Cell.Mine -> "*"
            Cell.None -> "C"
        }
    }
}
