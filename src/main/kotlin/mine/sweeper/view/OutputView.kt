package mine.sweeper.view

import mine.sweeper.field.domain.Field

object OutputView {
    fun printMap(fields: List<Field>) {
        println("지뢰찾기 게임 시작")
        val sortedFields = fields.sortedWith(compareBy({ it.position.x }, { it.position.y }))

        var currentRow = sortedFields.first().position.x
        for (field in sortedFields) {
            if (field.position.x != currentRow) {
                println()
                currentRow = field.position.x
            }
            print("${field.value} ")
        }
        println()
    }
}
