package minesweeper.view

import minesweeper.domain.cell.Dot
import minesweeper.domain.cell.Land
import minesweeper.domain.cell.Mine
import minesweeper.dto.MineBoardMatrix
import minesweeper.dto.Row

class OutputView {
    fun printStartGame() {
        println("지뢰찾기 게임 시작")
    }

    fun printMineBoard(matrix: MineBoardMatrix) {
        println()
        matrix.rows.forEach { printMineBoardRow(it) }
        println()
    }

    private fun printMineBoardRow(row: Row) {
        println(row.dots.joinToString(" ") { convertToSign(dot = it) })
    }

    private fun convertToSign(dot: Dot): String = when (dot) {
        is Mine -> if (dot.isHidden) "C" else "*"
        is Land -> if (dot.isHidden) "C" else dot.mineCount.value.toString()
    }

    fun printLoseGame() {
        println("Lose Game.")
    }

    fun printWinGame() {
        println("Win Game!")
    }
}
