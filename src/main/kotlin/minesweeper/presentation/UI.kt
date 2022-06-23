package minesweeper.presentation

import minesweeper.domain.Board
import minesweeper.domain.Cell

object UI {

    fun drawStartMessage() {
        println("지뢰찾기 게임 시작")
    }

    fun drawWinMessage() {
        println("Win Game.")
    }

    fun drawLoseMessage() {
        println("Lose Game.")
    }

    fun drawNotFoundMessage() {
        println("좌표를 다시 입력해주세요.")
    }

    fun drawAlreadyOpenedMessage() {
        println("이미 열려있습니다.")
    }

    fun drawBoard(board: Board) {
        board.groupByColumn().forEach { (_, row) ->
            println(row.joinToString(" ") { convertCellMark(it) })
        }
    }

    private fun convertCellMark(cell: Cell): String {
        if (!cell.isOpened()) return "C"

        return when (cell) {
            is Cell.Mine -> "*"
            is Cell.Block -> cell.aroundMineCount.toString()
        }
    }
}
