package view

import domain.Cell
import domain.MineSweeperBoard
import domain.Position

object ResultView {
    fun startMessage() {
        println("지뢰찾기 게임 시작")
    }

    fun board(board: MineSweeperBoard) {
        repeat(board.height) { y ->
            println(board.getRow(y).joinToString(" ") { cell(it) })
        }
    }

    private fun cell(cell: Cell): String {
        return when (cell) {
            is Cell.MineCell -> "*"
            is Cell.NormalCell -> "C"
        }
    }
}
