package mine.view

import mine.Board
import mine.cell.Cell
import mine.cell.NoneCell

object OutputView {
    const val CELL_NAME = "E"
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

    fun Cell.name(): String {
        return when (this) {
            is NoneCell -> if (this.isClicked) getNearMineCount().toString() else CELL_NAME
            else -> CELL_NAME
        }
    }
}
