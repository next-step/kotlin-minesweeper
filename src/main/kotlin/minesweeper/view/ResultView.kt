package minesweeper.view

import minesweeper.domain.Position
import minesweeper.domain.Positions

fun printStart() {
    println("지뢰찾기 게임 시작")
}

fun printResult(positions: Positions) {
    positions.positions.forEach { positionArray ->
        positionArray.forEach {
            printFormatting(it)
        }
        println()
    }
    println()
}

fun printLose() {
    println("Lose Game.")
}

fun printWin() {
    println("Win Game.")
}

private fun printFormatting(position: Position) {
    if (position.isOpened) {
        print("${position.value.toString().replace("-1", "*")} ")
        return
    }
    print("C ")
}
