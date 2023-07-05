package minesweeper.view

import minesweeper.domain.Positions

fun printStart() {
    println("지뢰찾기 게임 시작")
}

fun printResult(positions: Positions) {
    positions.positions.forEach {
        println(
            it.contentToString()
                .replace("-1", "*")
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
        )
    }
}
