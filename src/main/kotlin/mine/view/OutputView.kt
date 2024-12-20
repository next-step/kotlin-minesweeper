package mine.view

import mine.domain.Mine

object OutputView {
    fun gameStart(mine: Mine) {
        println("지뢰찾기 게임 시작")
        mine.minesweeper.forEach { row ->
            println(row.mineCells.joinToString(" ", transform = { it.value }))
        }
    }
}
