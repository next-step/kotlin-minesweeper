package minesweeper.view

import minesweeper.domain.Board

internal class OutputView {
    fun render(board: Board) {
        println("\n지뢰찾기 게임 시작")

        board.cellRows.forEach {
            println(it.cells.joinToString(" ") { it.display })
        }
    }
}
