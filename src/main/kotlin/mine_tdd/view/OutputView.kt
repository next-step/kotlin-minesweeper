package mine_tdd.view

import mine_tdd.Board
import mine_tdd.cell.MineCell

object OutputView {
    private const val MINE_CELL_NAME = "+"
    private const val CELL_NAME = "-"

    fun printBoard(board: Board) {
        println()
        for (i in 0..board.column()) {
            board.getRowCells(i).values().forEachIndexed { index, i ->
                when (i) {
                    is MineCell -> print("$MINE_CELL_NAME ")
                    else -> print("${i.nearMineCount} ")
                }
            }
            println()
        }
    }
}
