package minesweeper.view

import minesweeper.domain.Cell

class OutputView {

    fun printCells(cells: Array<Array<Cell>>) {
        println(OUTPUT_GAME_START_GUIDE)
        cells.forEach {
            printRow(it)
        }
    }

    private fun printRow(cellArray: Array<Cell>) {
        println(cellArray.joinToString(" ") { it.status().description })
    }

    companion object {
        private const val OUTPUT_GAME_START_GUIDE = "지뢰찾기 게임 시작"
    }
}
