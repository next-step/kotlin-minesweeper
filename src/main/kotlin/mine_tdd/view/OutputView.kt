package mine_tdd.view

import mine_tdd.Board
import mine_tdd.cell.MineCell

object OutputView {
    private const val CELL_NAME = "-"

    fun startGame() {
        println("\n지뢰찾기 게임 시작")
    }

    fun printBoard(board: Board) {
        println()
        for (i in 0..board.column()) {
            board.getRowCells(i).values().forEachIndexed { index, i ->
                when (i) {
                    is MineCell -> print("$CELL_NAME ")
                    else -> if (i.isOpen) print("${i.nearMineCount} ") else print("$CELL_NAME ")
                }
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
