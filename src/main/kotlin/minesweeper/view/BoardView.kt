package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.Mine
import minesweeper.domain.Opened

class BoardView(private val board: Board) {
    fun print() {
        board.rows().forEach {
            println(it.joinToString(" ") { cell -> getCharacter(cell) })
        }
    }

    private fun getCharacter(cell: Cell): String {
        return when (cell) {
            is Mine -> MINE
            is Opened -> OPENED
        }
    }

    companion object {
        private const val MINE = "\uD83D\uDCA5" // 💥
        private const val OPENED = "⬜"
    }
}
