package minesweeper.ui

import minesweeper.domain.CellFinder
import minesweeper.domain.Position
import minesweeper.domain.Size

object ResultView {

    private const val mine_symbol = "*"

    fun printMines(height: Size, width: Size, cellFinder: CellFinder) {
        println()
        println("지뢰찾기 게임 시작")
        height.getNumbers()
            .forEach { printRow(it, width, cellFinder) }
    }

    private fun printRow(rowNum: Size, width: Size, cellFinder: CellFinder) {
        width.getNumbers()
            .forEach {
                val cell = cellFinder.find(Position(rowNum, it)) ?: throw RuntimeException("출력 도중 알 수 없는 에러가 발생했습니다.")
                when (cell.isMine) {
                    true -> print("$mine_symbol ")
                    false -> print("${cellFinder.getAroundMinesCount(cell)} ")
                }
            }
        println()
    }
}
