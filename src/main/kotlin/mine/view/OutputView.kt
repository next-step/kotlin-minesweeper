package mine.view

import mine.Board

object OutputView {
    fun printBoard(board: Board) {
        println("\n지뢰찾기 게임 시작\n")
        for (i in 0..board.cells.column()) {
            board.cells.rowOfCells(i).values.forEach {
                print("${it.name()} ")
            }
            println()
        }
    }

    fun printLoseGame() {
        println("Lose Game.")
    }

    fun printWinGame() {
        println("Win Game.")
    }
}
