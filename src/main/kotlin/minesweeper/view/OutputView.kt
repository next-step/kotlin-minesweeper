package minesweeper.view

import minesweeper.domain.Cell
import minesweeper.domain.CellType
import minesweeper.domain.Cells

object OutputView {

    fun printGameBoard(cells: Cells) {
        println("지뢰찾기 게임 시작")
        cells.groupByPositionX().forEach {
            println(it.joinToString(" ") { cell -> cell.toPoint() })
        }
    }

    private fun Cell.toPoint(): String {
        return when (cellState.cellType) {
            CellType.NON_MINE -> cellState.mineCount.toString()
            CellType.MINE -> "*"
        }
    }
}
