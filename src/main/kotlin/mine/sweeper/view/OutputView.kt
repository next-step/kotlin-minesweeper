package mine.sweeper.view

import mine.sweeper.domain.value.GameStatus
import mine.sweeper.domain.Field
import mine.sweeper.domain.Fields
import mine.sweeper.domain.MineField
import mine.sweeper.domain.SafeField

object OutputView {
    fun noticeGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printMap(fields: Fields) {
        val sortedFields = fields.sortedList

        var currentRow = sortedFields.first().position.x
        for (field in sortedFields) {
            if (field.position.x != currentRow) {
                println()
                currentRow = field.position.x
            }
            printFieldStatus(field)
        }
        println()
    }

    private fun printFieldStatus(field: Field) {
        when {
            !field.checked -> print("C ")
            field is SafeField -> print("${field.value} ")
            field is MineField -> print("* ")
        }
    }

    fun noticeGameResult(status: GameStatus) {
        if (status == GameStatus.COMPLETE) {
            println("Win Game.")
        } else if (status == GameStatus.GAME_OVER) {
            println("Lose Game.")
        }
    }
}
