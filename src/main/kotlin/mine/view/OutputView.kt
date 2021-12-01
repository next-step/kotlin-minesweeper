package mine.view

import mine.Board

object OutputView {
    fun printBoard(board: Board) {
        println("\n지뢰찾기 게임 시작\n")
        for (i in 0..board.height.value) {
            board.cells.rowOfCells(i).values.forEach {
                print("${it.type.name()} ")
            }
            println()
        }
    }
}
