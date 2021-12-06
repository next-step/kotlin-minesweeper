package mine.view

import mine.Board
import mine.cell.MineCell
import mine.cell.NoneCell

object OutputView {
    fun printBoard(board: Board) {
        println("\n지뢰찾기 게임 시작\n")
        for (i in 0..board.cells.column()) {
            board.cells.rowOfCells(i).values.forEach {
                when (it) {
                    is NoneCell -> print("${it.mineCount} ")
                    is MineCell -> print("${it.name()} ")
                }
            }
            println()
        }
    }
}
