package minesweeper.view

import minesweeper.domain.MineSweeper

class ResultView {
    fun showMineSweeper(mineSweeper: MineSweeper) {
        println("지뢰찾기 게임 시작")

        mineSweeper.mineMap.values.forEach { row ->
            println(
                row.joinToString(" ") { cell ->
                    if (cell.isMine) "C"
                    else "*"
                }
            )
        }
    }
}
