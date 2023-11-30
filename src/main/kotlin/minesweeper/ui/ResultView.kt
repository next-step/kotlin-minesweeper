package minesweeper.ui

import minesweeper.domain.Size

object ResultView {

    private const val mine_symbol = "*"
    private const val none_mine_symbol = "C"

    /*
    fun printMines(height: Size, width: Size, mines: Mines) {
        println()
        println("지뢰찾기 게임 시작")
        for (rowNum in 1..height.value) {
            printMineRow(rowNum, width, mines)
            println()
        }
    }

    private fun printMineRow(x: Int, width: Size, mines: Mines) {
        for (y in 1..width.value) {
            printMine(x, y, mines)
        }
    }

    private fun printMine(x: Int, y: Int, mines: Mines) {
        return when (mines.contains(x, y)) {
            true -> print("$mine_symbol ")
            false -> print("$none_mine_symbol ")
        }
    }
     */
}
