package view

import domain.Blank
import domain.Board
import domain.Cell
import domain.Column
import domain.Game
import domain.Mine

object ResultView {
    fun printBoard(game: Game, board: Board) {
        println("지뢰찾기 게임 시작")

        board.cells.forEachIndexed { index, it ->
            print("${toStringCell(it)} ")

            if (isSpace(index, game.column)) println()
        }
    }

    private fun toStringCell(cell: Cell): String {
        return when (cell) {
            is Mine -> "*"
            is Blank -> "C"
        }
    }

    private fun isSpace(index: Int, column: Column): Boolean {
        return (index + 1) % column.value == 0
    }
}
