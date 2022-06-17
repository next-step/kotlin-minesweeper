package minesweeper.ui

import minesweeper.application.dto.MineFieldView
import minesweeper.application.dto.Row
import minesweeper.domain.field.Mine

object OutputView {
    fun printMineField(mineFieldView: MineFieldView) {
        println("지뢰찾기 게임 시작")
        mineFieldView.rows.forEach { printRow(it) }
    }

    private fun printRow(row: Row) {
        val rowString = row.value.map {
            if (it == Mine) {
                "*"
            } else {
                "C"
            }
        }

        println(rowString.joinToString())
    }
}
