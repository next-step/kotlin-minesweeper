package minesweeper.view

import minesweeper.model.Cell
import minesweeper.model.MineBoard
import minesweeper.model.MineMap.Companion.INIT_INDEX
import minesweeper.model.getDefaultCellPool

object OutputView {
    private const val MINE_SYMBOL = "*"
    private const val CELL_SPACE = " "
    private const val NEW_LINE = "\n"

    fun printMineBoard(mineBoard: MineBoard) {
        print("\n지뢰찾기 게임 시작")
        val mineMap = mineBoard.mineMap
        val cellPoolKeys = getDefaultCellPool(mineMap.rowSize, mineMap.columnSize).keys
        printMineBoardMap(cellPoolKeys, mineBoard)
    }

    private fun printMineBoardMap(cells: Set<Cell>, board: MineBoard) {
        val builder = StringBuilder()
        cells.forEach { cell ->
            builder.append(if (cell.x == INIT_INDEX) NEW_LINE else CELL_SPACE)
            builder.append(if (board.checkMine(cell)) MINE_SYMBOL else board.getNearCount(cell))
        }
        println(builder)
    }
}
