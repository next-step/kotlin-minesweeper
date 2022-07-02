package view

import domain.Board
import domain.Mine
import domain.Row

class GameView(val io: IO) {

    fun viewBoard(board: Board) {
        io.writeLn()
        io.writeLn("지뢰찾기 게임 시작")
        board.rows.forEach {
            io.writeLn(it.toBoardString(board))
        }
    }

    private fun Row.toBoardString(board: Board): String =
        cells.joinToString(CELL_SEPARATOR) {
            when (it) {
                is Mine -> MINE
                else -> "${board.mineCount(it)}"
            }
        }

    companion object {
        private const val MINE = "*"
        private const val CELL_SEPARATOR = " "
    }
}
