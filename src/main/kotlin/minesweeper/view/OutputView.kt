package minesweeper.view

import minesweeper.domain.MineSweeper
import minesweeper.domain.Mines
import minesweeper.domain.Position

object OutputView {

    fun printMineSweeper(mineSweeper: MineSweeper) {
        println("지뢰찾기 게임 시작")
        val mineMap = mineSweeper.mineMap
        val mines = mineSweeper.mines
        (1..mineMap.height()).map {
            val row = mineMap.createRow(it)
            printRow(row, mines)
        }
    }

    private fun printRow(row: List<Position>, mines: Mines) {
        row.forEach {
            if (mines.isMine(it))
                return@forEach print("* ")

            print("C ")
        }
        println()
    }
}
