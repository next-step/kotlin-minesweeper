package minesweeper.view

import minesweeper.domain.MineMap

object ResultView {
    fun printMineGame(mineMap: MineMap) {
        println()
        println("지뢰찾기 게임 시작")

        mineMap.map
            .chunked(mineMap.width.value)
            .map { line ->
                println(line.joinToString(" ") { it.symbol })
            }
    }
}
