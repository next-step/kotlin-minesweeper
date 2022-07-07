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
            is Opened -> numbers[cell.counter]
        }
    }

    companion object {
        private const val MINE = "\uD83D\uDCA5" // 💥
        private val numbers = listOf(
            "⬜", "1️⃣", "2️⃣", "3️⃣", "4️⃣", "5️⃣", "6️⃣", "7️⃣", "8️⃣"
        )
        private const val OPENED = "⬜"
    }
}
