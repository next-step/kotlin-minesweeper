package mine.sweeper.view

import mine.sweeper.domain.Field

object OutputView {
    fun printMap(fields: Array<Array<Field>>) {
        println("지뢰찾기 게임 시작")
        for (row in fields) {
            for (field in row) {
                print("${field.value} ")
            }
            println()
        }
    }
}
