package minesweeper.ui

import minesweeper.domain.Mines

object ResultView {

    private const val mine_symbol = "*"
    private const val none_mine_symbol = "C"

    fun printMines(height: Int, width: Int, mines: Mines) {
        println()
        println("지뢰찾기 게임 시작")
        for (rowNum in 1..height) {
            printMineRow(rowNum, width, mines)
            println()
        }
    }

    private fun printMineRow(x: Int, width: Int, mines: Mines) {
        for (y in 1..width) {
            printMine(x, y, mines)
        }
    }

    private fun printMine(x: Int, y: Int, mines: Mines) {
        return when (mines.contains(x, y)) {
            true -> print("$mine_symbol ")
            false -> print("$none_mine_symbol ")
        }
    }
}
