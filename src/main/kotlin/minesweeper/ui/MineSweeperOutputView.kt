package minesweeper.ui

import minesweeper.domain.board.Board
import minesweeper.domain.cell.Cell

object MineSweeperOutputView {

    fun display(board: Board) {
        println("지뢰찾기 게임 시작")
        println(board.print())
    }

    private fun Board.print(): String {
        return cells.toList()
            .groupBy({ it.first.row }, { it.second })
            .map { (_, cells) -> cells.joinToString(" ") { it.print() } }
            .joinToString("\n")
    }

    private fun Cell.print(): String = if (this.isMine()) "💣" else "🧱"
}
