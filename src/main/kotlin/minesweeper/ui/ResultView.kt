package minesweeper.ui

import minesweeper.domain.CellFinder
import minesweeper.domain.Position
import minesweeper.domain.Size

object ResultView {

    private const val mine_symbol = "*"
    private const val none_mine_symbol = "C"

    fun printMines(height: Size, width: Size, cellFinder: CellFinder) {
        println()
        println("지뢰찾기 게임 시작")
        height.getNumbers()
            .forEach { printRow(it, width, cellFinder) }
    }

    private fun printRow(rowNum: Size, width: Size, cellFinder: CellFinder) {
        width.getNumbers()
            .forEach {
                val cell = cellFinder.find(Position(rowNum, it))
                when (cell.isMine) {
                    true -> print("$mine_symbol ")
                    false -> print("$none_mine_symbol ")
                }
            }
        println()
    }
}
