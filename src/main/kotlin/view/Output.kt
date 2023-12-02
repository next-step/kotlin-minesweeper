package view

import domain.Cell

fun printStartMessage() {
    println("지뢰찾기 게임 시작")
}

fun printBoard(cells: List<List<Cell>>) {
    cells.forEach { row ->
        row.joinToString(" ") { it.toStr() }.let(::println)
    }
}

fun printResult(foundAll: Boolean) {
    when (foundAll) {
        true -> println("You Win.")
        false -> println("Lose Game.")
    }
}
