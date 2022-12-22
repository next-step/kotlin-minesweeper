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

        val printFields = field.print()

        println(printFields)
    }

    private fun Field.print(): String = this.rows.joinToString(separator = "\n") { row -> row.print() }
    private fun Row.print(): String = this.cells.joinToString(separator = " ") { it.desc() }
    private fun Cell.desc() = when (this.block) {
        is Land -> "C"
        is Mine -> "O"
    }
}
