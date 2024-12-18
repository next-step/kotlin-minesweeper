package minesweeper.view

import minesweeper.domain.Grid

object ResultView {
    fun renderBoard(grid: Grid) {
        println("지뢰찾기 게임 시작")
        grid.cells.forEach { row ->
            println(row.joinToString(" ") { it.toString() })
        }
    }
}
