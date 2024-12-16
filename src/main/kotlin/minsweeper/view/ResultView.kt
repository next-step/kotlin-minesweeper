package minsweeper.view

import minsweeper.domain.BoardLines
import minsweeper.domain.Cell

object ResultView {

    fun printBoard(boardLines: BoardLines) {
        println(buildString {
            append("지뢰찾기 게임 시작\n")
            append(boardLines.lines.joinToString(separator = "\n") { boardLine ->
                boardLine.cells.joinToString(separator = " ") { it.print() }
            })
        })
    }

    private fun Cell.print(): String = when (this) {
        is Cell.Island -> "${this.aroundMineCount}"
        Cell.Mine -> "*"
    }

}
