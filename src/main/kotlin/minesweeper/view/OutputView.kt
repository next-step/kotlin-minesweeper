package minesweeper.view

import minesweeper.model.Cell
import minesweeper.model.MineBoard

object OutputView {
    private const val MINE_SYMBOL = "*"
    private const val CELL_SPACE = " "

    fun printMineBoard(mineBoard: MineBoard) {
        println("\n지뢰찾기 게임 시작")
        val mineMap = mineBoard.mineMap
        mineMap.forEach { row -> printRow(row, mineBoard::checkMine) }
    }

    private fun printRow(row: List<Cell>, checkMine: (Cell) -> Boolean) {
        println(
            row.joinToString(CELL_SPACE) { cell ->
                if (checkMine(cell)) MINE_SYMBOL else "${cell.surroundingMineCount}"
            }
        )
    }
}
