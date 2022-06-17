package minesweeper.view

import minesweeper.domain.CellType
import minesweeper.domain.Cells

object OutputView {

    fun printGameBoard(cells: Cells) {
        println("지뢰찾기 게임 시작")
        cells.groupByPositionX().forEach {
            println(it.joinToString(" ") { cell -> cell.cellType.toPoint() })
        }
    }

    private fun CellType.toPoint(): String {
        return when (this) {
            CellType.NON_MINE -> "C"
            CellType.MINE -> "*"
        }
    }
}
