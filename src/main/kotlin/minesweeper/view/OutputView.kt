package minesweeper.view

import minesweeper.model.Cell
import minesweeper.model.MineBoard

object OutputView {
    private const val BLANK_SYMBOL = "C"
    private const val MINE_SYMBOL = "*"
    private const val CELL_SPACE = " "

    fun printMineBoard(mineBoard: MineBoard) {
        println("\n지뢰찾기 게임 시작")
        mineBoard.map.forEach { row -> printRow(row, mineBoard) }
    }

    private fun printRow(row: List<Cell>, board: MineBoard) {
        println(row.joinToString(CELL_SPACE) { cell ->
            if (board.checkMine(cell)) MINE_SYMBOL else BLANK_SYMBOL
        })
    }
}
