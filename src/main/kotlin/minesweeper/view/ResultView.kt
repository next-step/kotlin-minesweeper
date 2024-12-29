package minesweeper.view

import minesweeper.domain.Grid

object ResultView {
    fun printStartGameMessage() {
        println("지뢰찾기 게임 시작")
    }

    fun renderBoard(grid: Grid) {
        grid.cells.forEach { row ->
            println(row.joinToString(" ") { it.displayString() })
        }
    }

    fun printLoseGameMessage() {
        println("Lose Game")
    }
}
