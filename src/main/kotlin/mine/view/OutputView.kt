package mine.view

import mine.Board

object OutputView {
    fun startGame() {
        println("\n지뢰찾기 게임 시작")
    }

    fun printBoard(board: Board) {
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
