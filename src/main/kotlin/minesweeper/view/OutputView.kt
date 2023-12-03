package minesweeper.view

import minesweeper.domain.Cell
import minesweeper.domain.Empty
import minesweeper.domain.Mine
import minesweeper.domain.MineMap
import minesweeper.domain.MineMapMeta
import minesweeper.domain.OpenState
import minesweeper.domain.Position

object OutputView {
    private const val MINE_CELL_CHAR = "*"
    private const val CLOSED_CELL_CHAR = "C"

    fun printGameStartMsg() {
        println("\n지뢰 찾기 게임 시작")
    }

    fun printGameLoseMsg() {
        println("Lose Game.")
    }

    fun printOpenPositionMsg(position: Position) {
        println("open: ${position.y}, ${position.x}")
    }

    fun printMineMap(mineMapMeta: MineMapMeta, mineMap: MineMap) {
        for (row in Position.START_INDEX until mineMapMeta.height + 1) {
            printRowCells(mineMapMeta, mineMap, row)
        }
        println()
    }
    private fun printRowCells(mineMapMeta: MineMapMeta, mineMap: MineMap, row: Int) {
        for (col in Position.START_INDEX until mineMapMeta.width + 1) {
            val cell = mineMap.getCell(Position(row, col))
            if (cell.openState == OpenState.OPENED) printOpenedCell(cell)
            if (cell.openState == OpenState.CLOSED) printClosedCell()
        }
        println()
    }

    private fun printOpenedCell(cell: Cell) {
        when (cell) {
            is Mine -> print("$MINE_CELL_CHAR ")
            is Empty -> print("${cell.mineCount} ")
        }
    }

    private fun printClosedCell() {
        print("$CLOSED_CELL_CHAR ")
    }
}
