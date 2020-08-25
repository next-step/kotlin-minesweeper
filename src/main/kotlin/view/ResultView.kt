package view

import model.MinePlate

object ResultView {
    fun printMinePlate(minePlate: MinePlate) {
        println("지뢰찾기 게임 시작")
        minePlate.value.forEach { row ->
            run {
                row.column.blocks.forEach { col -> run { print(col) } }
                println()
            }
        }
    }
}
