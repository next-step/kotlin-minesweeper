package view

import domain.Field
import domain.GameStatus
import domain.Row
import domain.block.Block
import domain.block.Land
import domain.block.Mine

object ResultView {

    fun printGameStartWording() {
        println()
        println("지뢰찾기 게임 시작")
    }

    fun printGameField(field: Field) {
        println()
        val printFields = field.stringForPrint()

        println(printFields)
        println()
    }

    private fun Field.stringForPrint(): String = this.rows.joinToString(separator = "\n") { row -> row.stringForPrint() }
    private fun Row.stringForPrint(): String = this.cells.joinToString(separator = " ") { it.desc() }
    private fun Block.desc() = when (this) {
        is Land -> if (isOpened) {
            nearMineCount.toString()
        } else {
            "C"
        }

        is Mine -> "C"
    }

    fun printGameResult(result: GameStatus) {
        when (result) {
            GameStatus.WIN -> println("Win Game.")
            GameStatus.LOSE -> println("Lose Game.")
            GameStatus.PROGRESSING -> println("Not finished Game.")
        }
    }
}
