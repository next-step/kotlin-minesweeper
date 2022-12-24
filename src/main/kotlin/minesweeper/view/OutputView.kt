package minesweeper.view

import minesweeper.model.Cell
import minesweeper.model.MineBoard
import minesweeper.model.MineMap
import minesweeper.model.MineMap.Companion.INIT_INDEX

object OutputView {
    private const val MINE_SYMBOL = "*"
    private const val CELL_SPACE = " "
    private const val NEW_LINE = "\n"

    fun printMineBoard(mineBoard: MineBoard) {
        println("\n지뢰찾기 게임 시작")
        printMap(mineBoard.mineMap, mineBoard::checkMine)
    }

    private fun printMap(mineMap: MineMap, checkMine: (Cell) -> Boolean) {
        mineMap.forEach { cell ->
            print(if (cell.x == INIT_INDEX) NEW_LINE else CELL_SPACE)
            print(if (checkMine(cell)) MINE_SYMBOL else "${cell.surroundingMineCount}")
        }
    }
}
