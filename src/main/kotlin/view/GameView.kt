package view

import domain.Board
import domain.Mine
import domain.Row

class GameView(val io: IO) {

    fun viewBoard(board: Board) {
        io.write("지뢰찾기 게임 시작")
        board.rows.forEach {
            io.write(it.toBoardString())
        }
    }

    private fun Row.toBoardString(): String =
        cells.joinToString(CELL_SEPARATOR) {
            when (it) {
                is Mine -> MINE
                else -> COVERED
            }
        }

    companion object {
        private const val MINE = "*"
        private const val COVERED = "C"
        private const val CELL_SEPARATOR = " "
    }
}
