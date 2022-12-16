package view

import domain.Field
import domain.Row

object ResultView {

    fun printGameStart(field: Field) {
        println()
        println("지뢰찾기 게임 시작")

        val printFields = field.print()

        println(printFields)
    }

    private fun Field.print(): String = this.rows.joinToString(separator = "\n") { row -> row.print() }
    private fun Row.print(): String = this.cells.joinToString(separator = " ") { it.block.desc }
}
