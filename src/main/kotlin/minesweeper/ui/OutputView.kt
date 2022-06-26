package minesweeper.ui

import minesweeper.application.dto.MineFieldView
import minesweeper.application.dto.Row
import minesweeper.domain.field.Mine
import minesweeper.domain.field.NonMine

object OutputView {
    fun printMineField(mineFieldView: MineFieldView) {
        println("지뢰찾기 게임 시작")
        mineFieldView.rows.forEach { printRow(it) }
    }

    private fun printRow(row: Row) {
        val rowString = row.value.map {
            when (it) {
                is Mine -> if (it.isOpen) "*" else "C"
                is NonMine -> if (it.isOpen) it.mineCount else "C"
            }
        }

        println(rowString.joinToString())
    }

    fun printGameOver() {
        println("Lose Game...")
    }

    fun printGameEnd() {
        println("Win !!")
    }
}
