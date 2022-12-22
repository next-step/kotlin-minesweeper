package view

import domain.Cell
import domain.Field
import domain.Row
import domain.block.Land
import domain.block.Mine

object ResultView {
    fun printGameStart(field: Field) {
        println()
        println("지뢰찾기 게임 시작")

        val printFields = field.stringForPrint()

        println(printFields)
    }

    private fun Field.stringForPrint(): String = this.rows.joinToString(separator = "\n") { row -> row.stringForPrint() }
    private fun Row.stringForPrint(): String = this.cells.joinToString(separator = " ") { it.desc() }
    private fun Cell.desc() = when (block) {
        is Land -> block.nearMineCount.toString()
        is Mine -> "*"
    }
}
