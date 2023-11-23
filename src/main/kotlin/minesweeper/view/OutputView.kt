package minesweeper.view

import minesweeper.model.cell.Opening

object OutputView {
    fun printMap(minefield: Array<Array<Opening>>) {
        println("\n지뢰찾기 게임 시작")
        for (row in minefield) {
            printCols(row)
        }
    }

    private fun printCols(row: Array<Opening>) {
        for (it in row) {
            print("${it.value} ")
        }
        println()
    }
}
