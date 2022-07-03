package minesweeper.view

import minesweeper.domain.cell.Dot
import minesweeper.domain.cell.Land
import minesweeper.domain.cell.Mine
import minesweeper.dto.MineBoardMatrix
import minesweeper.dto.Row

class OutputView {
    fun printMineBoard(matrix: MineBoardMatrix) {
        println()
        println("지뢰찾기 게임 시작")
        matrix.rows.forEach { printMineBoardRow(it) }
    }

    private fun printMineBoardRow(row: Row) {
        println(row.dots.joinToString(" ") { convertToSign(dot = it) })
    }

    private fun convertToSign(dot: Dot): String = when (dot) {
        Mine -> "*"
        Land -> "C"
    }
}
