package view

import domain.Field

object ResultView {

    fun printGameStart(field: Field) {
        println()
        println("지뢰찾기 게임 시작")

        val printFields = field.rows.joinToString(separator = "\n") { row ->
            row.cells.joinToString(separator = " ") { it.block.desc }
        }

        println(printFields)
    }
}
