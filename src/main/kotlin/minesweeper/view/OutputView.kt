package minesweeper.view

import minesweeper.model.Cell
import minesweeper.model.MineMap.Companion.INIT_INDEX

object OutputView {
    private const val MINE_SYMBOL = "*"
    private const val CELL_SPACE = " "
    private const val NEW_LINE = "\n"

    fun printAllNearMineNumbers(allCells: List<Cell>, nearMineNumberMap: Map<Cell, Int>) {
        print("\n지뢰찾기 게임 시작")
        printMineBoardMap(allCells, nearMineNumberMap)
    }

    private fun printMineBoardMap(cellPoll: List<Cell>, nearMineNumberMap: Map<Cell, Int>) {
        val builder = StringBuilder()
        cellPoll.forEach { cell ->
            builder.append(if (cell.x == INIT_INDEX) NEW_LINE else CELL_SPACE)
            builder.append(nearMineNumberMap[cell] ?: MINE_SYMBOL)
        }
        println(builder)
    }
}
