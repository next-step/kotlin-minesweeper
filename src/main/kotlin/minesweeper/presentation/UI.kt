package minesweeper.presentation

import minesweeper.domain.Board
import minesweeper.domain.Cell

object UI {

    fun drawStartMessage() {
        println("지뢰찾기 게임 시작")
    }

    fun drawLoseMessage() {
        println("Lose Game.")
    }

    fun drawBoard(board: Board) {
        board.groupByColumn().forEach { (_, row) ->
            println(row.joinToString(" ") { convertCellMark(it) })
        }
    }

    private fun convertCellMark(cell: Cell): String {
        return when (cell) {
            is Cell.Mine -> "*"
            is Cell.Block -> cell.aroundMineCount.toString()
        }
    }
}
