package minesweeper.ui

import minesweeper.domain.MineFinder
import minesweeper.domain.Position
import minesweeper.domain.Size

object ResultView {

    private const val mine_symbol = "*"
    private const val none_mine_symbol = "C"

    fun printMines(height: Size, width: Size, mineFinder: MineFinder) {
        println()
        println("지뢰찾기 게임 시작")
        height.getNumbers()
            .forEach { printRow(it, width, mineFinder) }
    }

    private fun printRow(rowNum: Size, width: Size, mineFinder: MineFinder) {
        width.getNumbers()
            .forEach {
                val mine = mineFinder.find(Position(rowNum, it))
                when (mine.isMine) {
                    true -> print("$mine_symbol ")
                    false -> print("$none_mine_symbol ")
                }
            }
        println()
    }
}
